package com.nikitakrapo.progressif.auth.ui.registration

import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineExecutorFactory
import com.nikitakrapo.progressif.auth.ui.registration.RegistrationStore.Intent
import com.nikitakrapo.progressif.auth.ui.registration.RegistrationStore.Label
import com.nikitakrapo.progressif.auth.user.RegistrationError
import com.nikitakrapo.progressif.auth.user.UserRepository
import com.nikitakrapo.progressif.result.Result
import kotlinx.coroutines.launch

internal class RegistrationStoreFactory(
    private val storeFactory: StoreFactory,
    private val userRepository: UserRepository,
) {
    fun create(): RegistrationStore =
        object : RegistrationStore, Store<Intent, RegistrationState, Label> by storeFactory.create(
            name = "RegistrationStore",
            initialState = RegistrationState(
                username = "",
                email = "",
                password = "",
                isLoading = false,
                error = null,
            ),
            executorFactory = coroutineExecutorFactory {
                onIntent<Intent.UsernameChanged> {
                    dispatch(Msg.UsernameChanged(it.value))
                }

                onIntent<Intent.EmailChanged> {
                    dispatch(Msg.EmailChanged(it.value))
                }

                onIntent<Intent.PasswordChanged> {
                    dispatch(Msg.PasswordChanged(it.value))
                }

                onIntent<Intent.Submit> {
                    val current = state()
                    if (current.isLoading) return@onIntent
                    if (current.email.isBlank() || current.password.isBlank()) return@onIntent
                    dispatch(Msg.RegistrationStarted)
                    launch {
                        val result = userRepository.register(current.email, current.password)
                        when (result) {
                            is Result.Success -> dispatch(Msg.RegistrationSucceeded)
                            is Result.Failure -> dispatch(Msg.RegistrationFailed(result.error))
                        }
                    }
                }
            },
            reducer = { msg: Msg ->
                when (msg) {
                    is Msg.UsernameChanged -> copy(username = msg.value, error = null)
                    is Msg.EmailChanged -> copy(email = msg.value, error = null)
                    is Msg.PasswordChanged -> copy(password = msg.value, error = null)
                    Msg.RegistrationStarted -> copy(isLoading = true, error = null)
                    Msg.RegistrationSucceeded -> copy(isLoading = false)
                    is Msg.RegistrationFailed -> copy(isLoading = false, error = msg.error)
                }
            },
        ) {}

    private sealed interface Msg {
        data class UsernameChanged(val value: String) : Msg
        data class EmailChanged(val value: String) : Msg
        data class PasswordChanged(val value: String) : Msg
        data object RegistrationStarted : Msg
        data object RegistrationSucceeded : Msg
        data class RegistrationFailed(val error: RegistrationError) : Msg
    }
}
