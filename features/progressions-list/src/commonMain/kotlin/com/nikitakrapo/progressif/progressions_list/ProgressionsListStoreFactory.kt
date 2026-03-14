package com.nikitakrapo.progressif.progressions_list

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.nikitakrapo.progressif.progressions_list.ProgressionsListStore.Intent
import com.nikitakrapo.progressif.progressions_list.ProgressionsListStore.Label
import com.nikitakrapo.progressif.progressions_list.ProgressionsListStore.State

class ProgressionsListStoreFactory(
    private val storeFactory: StoreFactory,
) {

    fun create(): ProgressionsListStore =
        object : ProgressionsListStore, Store<Intent, State, Label> by storeFactory.create(
            name = "ProgressionsListStore",
            initialState = State(
                items = emptyList(),
            ),
            executorFactory = ::ExecutorImpl,
            reducer = ReducerImpl,
        ) {}

    private sealed interface Action

    private sealed interface Msg

    private class ExecutorImpl : CoroutineExecutor<Intent, Action, State, Msg, Label>() {
        override fun executeIntent(intent: Intent) {
        }

        override fun executeAction(action: Action) {
        }
    }

    private object ReducerImpl : Reducer<State, Msg> {
        override fun State.reduce(msg: Msg): State =
            when (msg) {
                else -> this
            }
    }
}
