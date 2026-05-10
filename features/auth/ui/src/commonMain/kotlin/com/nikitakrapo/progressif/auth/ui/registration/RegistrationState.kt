package com.nikitakrapo.progressif.auth.ui.registration

import com.nikitakrapo.progressif.auth.user.RegistrationError

data class RegistrationState(
    val username: String,
    val email: String,
    val password: String,
    val isLoading: Boolean,
    val error: RegistrationError?,
) {

    val submitButtonEnabled = username.isNotBlank() &&
            email.isNotBlank() &&
            password.isNotBlank() &&
            !isLoading
}
