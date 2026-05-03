package com.nikitakrapo.progressif.auth.ui.registration

import kotlinx.coroutines.flow.StateFlow

interface RegistrationComponent {

    val state: StateFlow<RegistrationState>

    fun onEmailChanged(value: String)

    fun onPasswordChanged(value: String)

    fun onSubmitClicked()

    fun onBackClick()
}
