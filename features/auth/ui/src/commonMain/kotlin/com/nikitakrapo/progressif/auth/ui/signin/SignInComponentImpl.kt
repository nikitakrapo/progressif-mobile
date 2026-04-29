package com.nikitakrapo.progressif.auth.ui.signin

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import com.nikitakrapo.progressif.auth.ui.signin.SignInStore.Intent
import com.nikitakrapo.progressif.auth.user.UserRepository
import com.nikitakrapo.progressif.di.Di
import kotlinx.coroutines.flow.StateFlow

class SignInComponentImpl(
    componentContext: ComponentContext,
    storeFactory: StoreFactory = Di.get(),
    userRepository: UserRepository = Di.get(),
) : SignInComponent, ComponentContext by componentContext {

    private val store = SignInStoreFactory(
        storeFactory = storeFactory,
        userRepository = userRepository,
    ).create()

    override val state: StateFlow<SignInState> = store.stateFlow

    override fun onEmailChanged(value: String) {
        store.accept(Intent.EmailChanged(value))
    }

    override fun onPasswordChanged(value: String) {
        store.accept(Intent.PasswordChanged(value))
    }

    override fun onSubmitClicked() {
        store.accept(Intent.Submit)
    }
}
