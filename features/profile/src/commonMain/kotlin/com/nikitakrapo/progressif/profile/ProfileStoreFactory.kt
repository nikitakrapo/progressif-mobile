package com.nikitakrapo.progressif.profile

import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineExecutorFactory

class ProfileStoreFactory(
    private val storeFactory: StoreFactory,
) {

    fun create(): ProfileStore =
        object : ProfileStore, Store<ProfileStore.Intent, ProfileState, ProfileStore.Label> by storeFactory.create(
            name = "ProfileStore",
            initialState = ProfileState(),
            executorFactory = coroutineExecutorFactory {
                onIntent<ProfileStore.Intent.Refresh> {
                    // TODO: implement refresh
                }
            },
            reducer = { msg: Msg ->
                when (msg) {
                    Msg.Placeholder -> this
                }
            },
        ) {}

    private sealed interface Msg {
        data object Placeholder : Msg
    }
}
