package com.nikitakrapo.progressif.auth.ui.signin

import kotlinx.coroutines.flow.StateFlow

interface SignInComponent {

    val state: StateFlow<SignInState>

    fun onEmailChanged(value: String)

    fun onPasswordChanged(value: String)

    fun onSubmitClicked()
}