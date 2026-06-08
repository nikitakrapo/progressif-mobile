package com.nikitakrapo.progressif.onboarding

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import com.nikitakrapo.progressif.di.Di
import com.nikitakrapo.progressif.onboarding.OnboardingStore.Intent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow

@OptIn(ExperimentalCoroutinesApi::class)
class OnboardingComponentImpl(
    componentContext: ComponentContext,
    storeFactory: StoreFactory = Di.get(),
) : OnboardingComponent, ComponentContext by componentContext {

    private val store = instanceKeeper.getStore {
        OnboardingStoreFactory(
            storeFactory = storeFactory,
        ).create()
    }

    override val state: StateFlow<OnboardingState> = store.stateFlow

    override fun onUsernameChange(value: String) {
        store.accept(Intent.UsernameChanged(value))
    }

    override fun onSubmitClick() {
        store.accept(Intent.Submit)
    }
}
