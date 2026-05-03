package com.nikitakrapo.progressif.auth.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.nikitakrapo.progressif.auth.ui.landing.AuthLandingScreen
import com.nikitakrapo.progressif.auth.ui.registration.RegistrationScreen
import com.nikitakrapo.progressif.auth.ui.signin.SignInScreen

@Composable
fun AuthenticationScreen(
    component: AuthenticationComponent,
) {
    val child by component.child.collectAsStateWithLifecycle()

    Children(stack = child) { created ->
        when (val instance = created.instance) {
            is AuthenticationComponent.Child.Landing -> AuthLandingScreen(instance.component)
            is AuthenticationComponent.Child.SignIn -> SignInScreen(instance.component)
            is AuthenticationComponent.Child.Registration -> RegistrationScreen(instance.component)
        }
    }
}
