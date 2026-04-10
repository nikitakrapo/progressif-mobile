package com.nikitakrapo.progressif.profile

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow

@OptIn(ExperimentalCoroutinesApi::class)
class ProfileComponentImpl(
    componentContext: ComponentContext,
    storeFactory: StoreFactory,
) : ProfileComponent, ComponentContext by componentContext {

    private val store = ProfileStoreFactory(
        storeFactory = storeFactory,
    ).create()

    override val state: StateFlow<ProfileState> = store.stateFlow

    override fun onRefresh() {
        store.accept(ProfileStore.Intent.Refresh)
    }
}
