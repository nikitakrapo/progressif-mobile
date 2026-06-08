package com.nikitakrapo.progressif.onboarding

import com.nikitakrapo.progressif.strings.Text

data class OnboardingState(
    val username: String,
    val isLoading: Boolean,
    val usernameError: Text?,
    val generalError: Text?,
) {

    val submitButtonEnabled = username.isNotBlank()
            && !isLoading
}
