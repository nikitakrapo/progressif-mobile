package com.nikitakrapo.progressif.onboarding

import com.arkivanov.mvikotlin.core.store.Store
import com.nikitakrapo.progressif.onboarding.OnboardingStore.Intent
import com.nikitakrapo.progressif.onboarding.OnboardingStore.Label

interface OnboardingStore : Store<Intent, OnboardingState, Label> {

    sealed interface Intent {

        data class UsernameChanged(val value: String) : Intent

        data object Submit : Intent
    }

    sealed interface Label {

    }
}
