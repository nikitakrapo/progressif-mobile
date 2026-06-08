package com.nikitakrapo.progressif.onboarding

data class OnboardingState(
    val username: String,
    val isLoading: Boolean,
) {

    val submitButtonEnabled = username.isNotBlank() && !isLoading
}
