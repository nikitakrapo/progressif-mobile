package com.nikitakrapo.progressif.onboarding

import kotlinx.coroutines.flow.StateFlow

interface OnboardingComponent {

    val state: StateFlow<OnboardingState>

    fun onUsernameChange(value: String)

    fun onSubmitClick()
}
