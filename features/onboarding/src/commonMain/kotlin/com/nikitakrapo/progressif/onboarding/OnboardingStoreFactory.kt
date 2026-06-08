package com.nikitakrapo.progressif.onboarding

import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineExecutorFactory
import com.nikitakrapo.progressf.strings.Res
import com.nikitakrapo.progressf.strings.error_unknown_message
import com.nikitakrapo.progressf.strings.onboarding_username_taken
import com.nikitakrapo.progressif.auth.user.PatchUserError
import com.nikitakrapo.progressif.auth.user.UserRepository
import com.nikitakrapo.progressif.onboarding.OnboardingStore.Intent
import com.nikitakrapo.progressif.onboarding.OnboardingStore.Label
import com.nikitakrapo.progressif.result.Result
import com.nikitakrapo.progressif.strings.Text
import kotlinx.coroutines.launch

internal class OnboardingStoreFactory(
    private val storeFactory: StoreFactory,
    private val userRepository: UserRepository,
) {
    fun create(): OnboardingStore =
        object : OnboardingStore, Store<Intent, OnboardingState, Label> by storeFactory.create(
            name = "OnboardingStore",
            initialState = OnboardingState(
                username = "",
                isLoading = false,
                usernameError = null,
                generalError = null,
            ),
            executorFactory = coroutineExecutorFactory {

                onIntent<Intent.UsernameChanged> {
                    dispatch(Msg.UsernameChanged(it.value))
                }

                onIntent<Intent.Submit> {
                    val current = state()
                    if (current.isLoading) return@onIntent
                    if (current.username.isBlank()) return@onIntent
                    dispatch(Msg.PatchStarted)
                    launch {
                        val result = userRepository.patchUser(current.username)
                        when (result) {
                            is Result.Success -> dispatch(Msg.PatchSucceeded)
                            is Result.Failure -> dispatch(Msg.PatchFailed(result.error))
                        }
                    }
                }
            },
            reducer = { msg: Msg ->
                when (msg) {
                    is Msg.UsernameChanged -> copy(
                        username = msg.value,
                        usernameError = null,
                        generalError = null,
                    )
                    is Msg.PatchFailed -> copy(
                        isLoading = false,
                        usernameError = when (msg.patchUserError) {
                            PatchUserError.UsernameTaken -> Text.StringRes(Res.string.onboarding_username_taken)
                            PatchUserError.Unknown -> null
                        },
                        generalError = when (msg.patchUserError) {
                            PatchUserError.UsernameTaken -> null
                            PatchUserError.Unknown -> Text.StringRes(Res.string.error_unknown_message)
                        },
                    )

                    Msg.PatchStarted -> copy(
                        isLoading = true,
                        usernameError = null,
                        generalError = null,
                    )
                    Msg.PatchSucceeded -> copy(
                        isLoading = false,
                        usernameError = null,
                        generalError = null,
                    )
                }
            },
        ) {}

    private sealed interface Msg {
        data class UsernameChanged(val value: String) : Msg
        data object PatchStarted : Msg
        data object PatchSucceeded : Msg
        data class PatchFailed(val patchUserError: PatchUserError) : Msg
    }
}
