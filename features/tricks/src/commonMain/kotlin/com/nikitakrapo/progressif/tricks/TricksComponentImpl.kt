@file:OptIn(ExperimentalCoroutinesApi::class)

package com.nikitakrapo.progressif.tricks

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import com.nikitakrapo.progressif.repositories.tricks.TricksRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow

class TricksComponentImpl(
    componentContext: ComponentContext,
    storeFactory: StoreFactory,
    tricksRepository: TricksRepository,
) : TricksComponent, ComponentContext by componentContext {

    private val store = TricksStoreFactory(
        storeFactory = storeFactory,
        tricksRepository = tricksRepository,
    ).create()

    override val state: StateFlow<TricksState> = store.stateFlow

    override fun onRefresh() {
        store.accept(TricksStore.Intent.Refresh)
    }
}
