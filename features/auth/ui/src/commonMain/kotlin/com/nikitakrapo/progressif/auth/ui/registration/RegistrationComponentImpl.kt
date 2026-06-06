package com.nikitakrapo.progressif.auth.ui.registration

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import com.nikitakrapo.progressif.auth.ui.registration.RegistrationStore.Intent
import com.nikitakrapo.progressif.auth.user.UserRepository
import com.nikitakrapo.progressif.di.Di
import kotlinx.coroutines.flow.StateFlow

class RegistrationComponentImpl(
    componentContext: ComponentContext,
    private val navigateBack: () -> Unit,
    storeFactory: StoreFactory = Di.get(),
    userRepository: UserRepository = Di.get(),
) : RegistrationComponent, ComponentContext by componentContext {

    private val store = instanceKeeper.getStore {
        RegistrationStoreFactory(
            storeFactory = storeFactory,
            userRepository = userRepository,
        ).create()
    }

    override val state: StateFlow<RegistrationState> = store.stateFlow

    override fun onUsernameChange(value: String) {
        store.accept(Intent.UsernameChanged(value))
    }

    override fun onEmailChange(value: String) {
        store.accept(Intent.EmailChanged(value))
    }

    override fun onPasswordChange(value: String) {
        store.accept(Intent.PasswordChanged(value))
    }

    override fun onSubmitClick() {
        store.accept(Intent.Submit)
    }

    override fun onBackClick() {
        navigateBack()
    }
}
