package com.nikitakrapo.progressif.progressions_list

import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineBootstrapper
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineExecutorFactory
import com.nikitakrapo.progressf.strings.Res
import com.nikitakrapo.progressf.strings.progressions_list_error_text
import com.nikitakrapo.progressif.domain.models.Progression
import com.nikitakrapo.progressif.domain.models.error.FetchError
import com.nikitakrapo.progressif.repositories.progressions.ProgressionsRepository
import com.nikitakrapo.progressif.progressions_list.ProgressionsListItem.AddProgressionItem
import com.nikitakrapo.progressif.progressions_list.ProgressionsListItem.ProgressionItem
import com.nikitakrapo.progressif.progressions_list.ProgressionsListStore.Intent
import com.nikitakrapo.progressif.progressions_list.ProgressionsListStore.Label
import com.nikitakrapo.progressif.strings.Text.StringRes
import com.nikitakrapo.progressif.ui.errors.getUserMessage
import kotlinx.coroutines.launch

class ProgressionsListStoreFactory(
    private val storeFactory: StoreFactory,
    private val progressionsRepository: ProgressionsRepository,
) {

    fun create(): ProgressionsListStore =
        object : ProgressionsListStore, Store<Intent, ProgressionsListState, Label> by storeFactory.create(
            name = "ProgressionsListStore",
            initialState = ProgressionsListState(
                items = emptyList(),
                isLoading = true,
                errorText = null,
            ),
            bootstrapper = coroutineBootstrapper {
                launch {
                    dispatch(Action.LoadProgressionsList)
                }
            },
            executorFactory = coroutineExecutorFactory {

                onIntent<Intent.Refresh> {
                    dispatch(Msg.StartedLoading)
                    forward(Action.LoadProgressionsList)
                }

                onIntent<Intent.ProgressionClick> {
                    publish(Label.OpenProgressionDetails(it.progression))
                }

                onIntent<Intent.AddProgressionClick> {
                    publish(Label.OpenAddProgression)
                }

                onAction<Action.LoadProgressionsList> {
                    launch {
                        progressionsRepository.getProgressions()
                            .fold(
                                onSuccess = {
                                    dispatch(Msg.ProgressionsLoaded(it))
                                },
                                onFailure = {
                                    val label = Label.ShowSnackbar(it.getUserMessage())
                                    publish(label)

                                    dispatch(Msg.ErrorReceived(it))
                                },
                            )
                    }
                }
            },
            reducer = { msg: Msg ->
                when (msg) {
                    is Msg.ProgressionsLoaded -> copy(
                        items = if (msg.progressions.isEmpty()) {
                            listOf(AddProgressionItem)
                        } else {
                            msg.progressions.map(::ProgressionItem)
                        },
                        isLoading = false,
                        errorText = null,
                    )
                    is Msg.ErrorReceived -> copy(
                        isLoading = false,
                        errorText = StringRes(Res.string.progressions_list_error_text),
                    )
                    Msg.StartedLoading -> copy(
                        isLoading = true,
                    )
                }
            },
        ) {}

    private sealed interface Action {

        data object LoadProgressionsList : Action
    }

    private sealed interface Msg {

        data object StartedLoading : Msg

        data class ProgressionsLoaded(val progressions: List<Progression>) : Msg

        data class ErrorReceived(val error: FetchError) : Msg
    }
}
