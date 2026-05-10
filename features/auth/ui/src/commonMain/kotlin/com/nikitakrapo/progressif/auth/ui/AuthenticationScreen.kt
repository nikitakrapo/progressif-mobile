package com.nikitakrapo.progressif.auth.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.nikitakrapo.progressif.auth.ui.landing.AuthLandingScreen
import com.nikitakrapo.progressif.auth.ui.registration.RegistrationScreen
import com.nikitakrapo.progressif.auth.ui.login.LoginScreen

@Composable
fun AuthenticationScreen(
    component: AuthenticationComponent,
) {
    val child by component.child.collectAsStateWithLifecycle()

    Children(
        stack = child,
        animation = stackAnimation(slide())
    ) { created ->
        when (val instance = created.instance) {
            is AuthenticationComponent.Child.Landing -> AuthLandingScreen(instance.component)
            is AuthenticationComponent.Child.Login -> LoginScreen(instance.component)
            is AuthenticationComponent.Child.Registration -> RegistrationScreen(instance.component)
        }
    }
}
