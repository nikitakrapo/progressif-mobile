package com.nikitakrapo.progressif.profile

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import com.nikitakrapo.progressif.auth.user.UserRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow

@OptIn(ExperimentalCoroutinesApi::class)
class ProfileComponentImpl(
    componentContext: ComponentContext,
    storeFactory: StoreFactory,
    userRepository: UserRepository,
) : ProfileComponent, ComponentContext by componentContext {

    private val store = ProfileStoreFactory(
        storeFactory = storeFactory,
        userRepository = userRepository,
    ).create()

    init {
        store.accept(ProfileStore.Intent.Refresh)
    }

    override val state: StateFlow<ProfileState> = store.stateFlow

    override fun accept(intent: ProfileStore.Intent) {
        store.accept(intent)
    }
}
