package com.nikitakrapo.progressif.auth.ui.registration

import com.nikitakrapo.progressif.strings.Text

data class RegistrationState(
    val email: String,
    val password: String,
    val isLoading: Boolean,
    val error: RegistrationErrorState?,
) {

    val submitButtonEnabled = email.isNotBlank()
            && password.isNotBlank()
            && !isLoading
}

data class RegistrationErrorState(
    val emailError: Text?,
    val passwordError: Text?,
    val generalError: Text?,
    val showLogInPrompt: Boolean,
)