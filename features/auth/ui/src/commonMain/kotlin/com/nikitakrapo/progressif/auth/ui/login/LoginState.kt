package com.nikitakrapo.progressif.auth.ui.login

import com.nikitakrapo.progressif.strings.Text

data class LoginState(
    val email: String,
    val password: String,
    val isLoading: Boolean,
    val error: LoginErrorState?,
) {

    val submitButtonEnabled = email.isNotBlank()
            && password.isNotBlank()
            && !isLoading
}

data class LoginErrorState(
    val generalError: Text?,
)
