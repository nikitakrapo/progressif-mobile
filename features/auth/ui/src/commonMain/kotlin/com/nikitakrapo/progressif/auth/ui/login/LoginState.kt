package com.nikitakrapo.progressif.auth.ui.login

data class LoginState(
    val email: String,
    val password: String,
    val isLoading: Boolean,
) {

    val submitButtonEnabled = email.isNotBlank()
            && password.isNotBlank()
            && !isLoading
}
