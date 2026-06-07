package com.nikitakrapo.progressif.auth.ui.login

import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineExecutorFactory
import com.nikitakrapo.progressf.strings.Res
import com.nikitakrapo.progressf.strings.login_error_invalid_credentials
import com.nikitakrapo.progressf.strings.login_error_unknown
import com.nikitakrapo.progressif.auth.ui.login.LoginStore.Intent
import com.nikitakrapo.progressif.auth.ui.login.LoginStore.Label
import com.nikitakrapo.progressif.auth.user.LoginError
import com.nikitakrapo.progressif.auth.user.UserRepository
import com.nikitakrapo.progressif.result.Result
import com.nikitakrapo.progressif.strings.Text.StringRes
import kotlinx.coroutines.launch

internal class LoginStoreFactory(
    private val storeFactory: StoreFactory,
    private val userRepository: UserRepository,
) {
    fun create(): LoginStore =
        object : LoginStore, Store<Intent, LoginState, Label> by storeFactory.create(
            name = "LoginStore",
            initialState = LoginState(
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
                    dispatch(Msg.LoginStarted)
                    launch {
                        val result = userRepository.login(current.email, current.password)
                        when (result) {
                            is Result.Success -> dispatch(Msg.LoginSucceeded)
                            is Result.Failure -> dispatch(Msg.LoginFailed(result.error))
                        }
                    }
                }
            },
            reducer = { msg: Msg ->
                when (msg) {
                    is Msg.EmailChanged -> copy(email = msg.value, error = null)
                    is Msg.PasswordChanged -> copy(password = msg.value, error = null)
                    Msg.LoginStarted -> copy(isLoading = true, error = null)
                    Msg.LoginSucceeded -> copy(isLoading = false)
                    is Msg.LoginFailed -> copy(isLoading = false, error = msg.error.extractErrorState())
                }
            },
        ) {}

    private sealed interface Msg {
        data class EmailChanged(val value: String) : Msg
        data class PasswordChanged(val value: String) : Msg
        data object LoginStarted : Msg
        data object LoginSucceeded : Msg
        data class LoginFailed(val error: LoginError) : Msg
    }

    private fun LoginError.extractErrorState(): LoginErrorState {
        return LoginErrorState(
            generalError = when (this) {
                LoginError.InvalidCredentials -> StringRes(Res.string.login_error_invalid_credentials)
                LoginError.Unknown -> StringRes(Res.string.login_error_unknown)
            },
        )
    }
}
