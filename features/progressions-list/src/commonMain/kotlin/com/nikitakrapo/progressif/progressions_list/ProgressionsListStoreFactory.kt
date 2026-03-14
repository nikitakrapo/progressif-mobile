package com.nikitakrapo.progressif.progressions_list

import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineBootstrapper
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineExecutorFactory
import com.nikitakrapo.progressf.strings.progressions_list_error
import com.nikitakrapo.progressif.domain.models.Progression
import com.nikitakrapo.progressif.domain.models.error.FetchError
import com.nikitakrapo.progressif.network.repositories.progressions.ProgressionsRepository
import com.nikitakrapo.progressif.progressions_list.ProgressionsListStore.Intent
import com.nikitakrapo.progressif.progressions_list.ProgressionsListStore.Label
import com.nikitakrapo.progressif.strings.Text
import com.nikitakrapo.progressif.ui.errors.getUserMessage
import kotlinx.coroutines.launch
import progressif.features.progressions_list.generated.resources.Res

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
                        items = msg.progressions,
                        isLoading = false,
                        errorText = null,
                    )
                    is Msg.ErrorReceived -> copy(
                        isLoading = false,
                        errorText = Text.StringRes(Res.string.progressions_list_error),
                    )
                }
            },
        ) {}

    private sealed interface Action {

        data object LoadProgressionsList : Action
    }

    private sealed interface Msg {

        data class ProgressionsLoaded(val progressions: List<Progression>) : Msg

        data class ErrorReceived(val error: FetchError) : Msg
    }
}
