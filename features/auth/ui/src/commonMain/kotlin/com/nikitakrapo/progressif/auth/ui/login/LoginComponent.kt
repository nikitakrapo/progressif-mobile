package com.nikitakrapo.progressif.auth.ui.login

import kotlinx.coroutines.flow.StateFlow

interface LoginComponent {

    val state: StateFlow<LoginState>

    fun onEmailChange(value: String)

    fun onPasswordChange(value: String)

    fun onSubmitClicked()
}
