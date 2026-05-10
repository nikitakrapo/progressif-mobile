package com.nikitakrapo.progressif.auth.ui.login

import kotlinx.coroutines.flow.StateFlow

interface LoginComponent {

    val state: StateFlow<LoginState>

    fun onEmailChanged(value: String)

    fun onPasswordChanged(value: String)

    fun onSubmitClicked()
}
