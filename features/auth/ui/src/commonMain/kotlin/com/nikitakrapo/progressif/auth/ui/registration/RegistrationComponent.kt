package com.nikitakrapo.progressif.auth.ui.registration

import kotlinx.coroutines.flow.StateFlow

interface RegistrationComponent {

    val state: StateFlow<RegistrationState>

    fun onEmailChange(value: String)

    fun onPasswordChange(value: String)

    fun onSubmitClick()

    fun onLoginClick()

    fun onBackClick()
}
