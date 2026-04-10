@file:OptIn(ExperimentalCoroutinesApi::class)

package com.nikitakrapo.progressif.progressions_list

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import com.nikitakrapo.progressif.network.repositories.progressions.ProgressionsRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow

class ProgressionsListComponentImpl(
    componentContext: ComponentContext,
    storeFactory: StoreFactory,
    progressionsRepository: ProgressionsRepository,
) : ProgressionsListComponent, ComponentContext by componentContext {

    private val store = ProgressionsListStoreFactory(
        storeFactory = storeFactory,
        progressionsRepository = progressionsRepository,
    ).create()

    override val state: StateFlow<ProgressionsListState> = store.stateFlow

    override fun onRefresh() {
        store.accept(ProgressionsListStore.Intent.Refresh)
    }

    override fun onProgressionClick(index: Int) {
        TODO("Not yet implemented")
    }

    override fun onAddProgressionClick() {
        TODO("Not yet implemented")
    }
}