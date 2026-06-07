package com.nikitakrapo.progressif.auth.ui.registration

import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineExecutorFactory
import com.nikitakrapo.progressf.strings.Res
import com.nikitakrapo.progressf.strings.registration_error_email_already_in_use
import com.nikitakrapo.progressf.strings.registration_error_invalid_email
import com.nikitakrapo.progressf.strings.registration_error_weak_password
import com.nikitakrapo.progressif.auth.ui.registration.RegistrationStore.Intent
import com.nikitakrapo.progressif.auth.ui.registration.RegistrationStore.Label
import com.nikitakrapo.progressif.auth.user.RegistrationError
import com.nikitakrapo.progressif.auth.user.RegistrationError.EmailError
import com.nikitakrapo.progressif.auth.user.UserRepository
import com.nikitakrapo.progressif.result.Result
import com.nikitakrapo.progressif.strings.Text
import kotlinx.coroutines.launch

internal class RegistrationStoreFactory(
    private val storeFactory: StoreFactory,
    private val userRepository: UserRepository,
) {
    fun create(): RegistrationStore =
        object : RegistrationStore, Store<Intent, RegistrationState, Label> by storeFactory.create(
            name = "RegistrationStore",
            initialState = RegistrationState(
                email = "",
                password = "",
                isLoading = false,
                error = null,
            ),
            executorFactory = coroutineExecutorFactory {

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
                    is Msg.EmailChanged -> copy(email = msg.value, error = null)
                    is Msg.PasswordChanged -> copy(password = msg.value, error = null)
                    Msg.RegistrationStarted -> copy(isLoading = true, error = null)
                    Msg.RegistrationSucceeded -> copy(isLoading = false)
                    is Msg.RegistrationFailed -> copy(isLoading = false, error = msg.error.extractErrorState())
                }
            },
        ) {}

    private sealed interface Msg {
        data class EmailChanged(val value: String) : Msg
        data class PasswordChanged(val value: String) : Msg
        data object RegistrationStarted : Msg
        data object RegistrationSucceeded : Msg
        data class RegistrationFailed(val error: RegistrationError) : Msg
    }

    private fun RegistrationError.extractErrorState(): RegistrationErrorState {
        return RegistrationErrorState(
            emailError = when (emailError) {
                EmailError.Invalid -> Text.StringRes(Res.string.registration_error_invalid_email)
                EmailError.AlreadyInUse -> Text.StringRes(Res.string.registration_error_email_already_in_use)
                null -> null
            },
            passwordError = when (passwordError) {
                RegistrationError.PasswordError.Weak -> Text.StringRes(Res.string.registration_error_weak_password)
                null -> null
            },
            generalError = when {
                emailError == EmailError.AlreadyInUse -> Text.StringRes(Res.string.registration_error_invalid_email)
                else -> null
            },
            showLogInPrompt = emailError == EmailError.AlreadyInUse,
        )
    }
}
