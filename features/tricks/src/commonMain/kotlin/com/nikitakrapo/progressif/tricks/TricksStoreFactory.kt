package com.nikitakrapo.progressif.tricks

import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineBootstrapper
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineExecutorFactory
import com.nikitakrapo.progressf.strings.Res
import com.nikitakrapo.progressf.strings.tricks_error_text
import com.nikitakrapo.progressif.domain.models.Trick
import com.nikitakrapo.progressif.domain.models.error.FetchError
import com.nikitakrapo.progressif.repositories.tricks.TricksRepository
import com.nikitakrapo.progressif.strings.Text.StringRes
import com.nikitakrapo.progressif.tricks.TricksStore.Intent
import com.nikitakrapo.progressif.tricks.TricksStore.Label
import com.nikitakrapo.progressif.ui.errors.getUserMessage
import kotlinx.coroutines.launch

class TricksStoreFactory(
    private val storeFactory: StoreFactory,
    private val tricksRepository: TricksRepository,
) {

    fun create(): TricksStore =
        object : TricksStore, Store<Intent, TricksState, Label> by storeFactory.create(
            name = "TricksStore",
            initialState = TricksState(
                tricks = emptyList(),
                isLoading = true,
                errorText = null,
            ),
            bootstrapper = coroutineBootstrapper {
                launch {
                    dispatch(Action.LoadTricks)
                }
            },
            executorFactory = coroutineExecutorFactory {

                onIntent<Intent.Refresh> {
                    dispatch(Msg.StartedLoading)
                    forward(Action.LoadTricks)
                }

                onAction<Action.LoadTricks> {
                    launch {
                        tricksRepository.getTricks()
                            .fold(
                                onSuccess = {
                                    dispatch(Msg.TricksLoaded(it))
                                },
                                onFailure = {
                                    publish(Label.ShowSnackbar(it.getUserMessage()))
                                    dispatch(Msg.ErrorReceived(it))
                                },
                            )
                    }
                }
            },
            reducer = { msg: Msg ->
                when (msg) {
                    is Msg.TricksLoaded -> copy(
                        tricks = msg.tricks,
                        isLoading = false,
                        errorText = null,
                    )
                    is Msg.ErrorReceived -> copy(
                        isLoading = false,
                        errorText = StringRes(Res.string.tricks_error_text),
                    )
                    Msg.StartedLoading -> copy(
                        isLoading = true,
                    )
                }
            },
        ) {}

    private sealed interface Action {

        data object LoadTricks : Action
    }

    private sealed interface Msg {

        data object StartedLoading : Msg

        data class TricksLoaded(val tricks: List<Trick>) : Msg

        data class ErrorReceived(val error: FetchError) : Msg
    }
}
