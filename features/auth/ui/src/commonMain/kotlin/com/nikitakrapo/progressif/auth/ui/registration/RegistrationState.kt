package com.nikitakrapo.progressif.auth.ui.registration

import com.nikitakrapo.progressif.auth.user.RegistrationError

data class RegistrationState(
    val email: String,
    val password: String,
    val isLoading: Boolean,
    val error: RegistrationError?,
) {

    val submitButtonEnabled = email.isNotBlank() &&
            password.isNotBlank() &&
            !isLoading
}
