package com.nikitakrapo.progressif.onboarding

import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineExecutorFactory
import com.nikitakrapo.progressif.onboarding.OnboardingStore.Intent
import com.nikitakrapo.progressif.onboarding.OnboardingStore.Label

internal class OnboardingStoreFactory(
    private val storeFactory: StoreFactory,
) {
    fun create(): OnboardingStore =
        object : OnboardingStore, Store<Intent, OnboardingState, Label> by storeFactory.create(
            name = "OnboardingStore",
            initialState = OnboardingState(
                username = "",
                isLoading = false,
            ),
            executorFactory = coroutineExecutorFactory {

                onIntent<Intent.UsernameChanged> {
                    dispatch(Msg.UsernameChanged(it.value))
                }

                onIntent<Intent.Submit> {
                    val current = state()
                    if (current.isLoading) return@onIntent
                    if (current.username.isBlank()) return@onIntent
                    // TODO: persist the username and advance the onboarding flow
                }
            },
            reducer = { msg: Msg ->
                when (msg) {
                    is Msg.UsernameChanged -> copy(username = msg.value)
                }
            },
        ) {}

    private sealed interface Msg {
        data class UsernameChanged(val value: String) : Msg
    }
}
