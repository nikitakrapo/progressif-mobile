package com.nikitakrapo.progressif.auth.ui

import com.arkivanov.decompose.router.stack.ChildStack
import com.nikitakrapo.progressif.auth.ui.landing.AuthLandingComponent
import com.nikitakrapo.progressif.auth.ui.registration.RegistrationComponent
import com.nikitakrapo.progressif.auth.ui.signin.SignInComponent
import kotlinx.coroutines.flow.StateFlow

interface AuthenticationComponent {

    val child: StateFlow<ChildStack<*, Child>>

    sealed interface Child {

        data class Landing(val component: AuthLandingComponent) : Child

        data class SignIn(val component: SignInComponent) : Child

        data class Registration(val component: RegistrationComponent) : Child
    }
}