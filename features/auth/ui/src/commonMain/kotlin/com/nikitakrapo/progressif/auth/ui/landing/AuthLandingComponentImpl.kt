package com.nikitakrapo.progressif.auth.ui.landing

import com.arkivanov.decompose.ComponentContext

class AuthLandingComponentImpl(
    componentContext: ComponentContext,
    private val openLogin: () -> Unit,
    private val openRegistration: () -> Unit,
) : AuthLandingComponent, ComponentContext by componentContext {

    override fun onLoginClick() {
        openLogin()
    }

    override fun onRegisterClick() {
        openRegistration()
    }
}
