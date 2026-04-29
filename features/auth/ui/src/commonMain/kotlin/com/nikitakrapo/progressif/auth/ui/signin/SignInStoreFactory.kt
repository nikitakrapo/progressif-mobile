package com.nikitakrapo.progressif.auth.ui.signin

import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineBootstrapper
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineExecutorFactory
import com.nikitakrapo.progressif.auth.ui.signin.SignInStore.Intent
import com.nikitakrapo.progressif.auth.ui.signin.SignInStore.Label
import com.nikitakrapo.progressif.auth.user.UserRepository
import io.github.aakira.napier.Napier
import kotlinx.coroutines.launch

internal class SignInStoreFactory(
    private val storeFactory: StoreFactory,
    private val userRepository: UserRepository,
) {
    fun create(): SignInStore =
        object : SignInStore, Store<Intent, SignInState, Label> by storeFactory.create(
            name = "ProgressionsListStore",
            initialState = SignInState(
                email = "",
                password = "",
            ),
            bootstrapper = coroutineBootstrapper {
            },
            executorFactory = coroutineExecutorFactory {
                onIntent<Intent.EmailChanged> {
                    dispatch(Msg.EmailChanged(it.value))
                }

                onIntent<Intent.PasswordChanged> {
                    dispatch(Msg.PasswordChanged(it.value))
                }

                onIntent<Intent.Submit> {
                    launch {
                        val loginResult = userRepository.signIn(state().email, state().password)
                        Napier.d { "Login result: $loginResult" }
                    }
                }
            },
            reducer = { msg: Msg ->
                when (msg) {
                    is Msg.EmailChanged -> copy(email = msg.value)
                    is Msg.PasswordChanged -> copy(password = msg.value)
                }
            },
        ) {}

    private sealed interface Action {

    }

    private sealed interface Msg {
        data class EmailChanged(val value: String) : Msg
        data class PasswordChanged(val value: String) : Msg
    }
}