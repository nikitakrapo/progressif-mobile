package com.nikitakrapo.progressif.auth.ui.login

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import com.nikitakrapo.progressif.auth.ui.login.LoginStore.Intent
import com.nikitakrapo.progressif.auth.user.UserRepository
import com.nikitakrapo.progressif.di.Di
import kotlinx.coroutines.flow.StateFlow

class LoginComponentImpl(
    componentContext: ComponentContext,
    storeFactory: StoreFactory = Di.get(),
    userRepository: UserRepository = Di.get(),
) : LoginComponent, ComponentContext by componentContext {

    private val store = LoginStoreFactory(
        storeFactory = storeFactory,
        userRepository = userRepository,
    ).create()

    override val state: StateFlow<LoginState> = store.stateFlow

    override fun onEmailChange(value: String) {
        store.accept(Intent.EmailChanged(value))
    }

    override fun onPasswordChange(value: String) {
        store.accept(Intent.PasswordChanged(value))
    }

    override fun onSubmitClicked() {
        store.accept(Intent.Submit)
    }
}
