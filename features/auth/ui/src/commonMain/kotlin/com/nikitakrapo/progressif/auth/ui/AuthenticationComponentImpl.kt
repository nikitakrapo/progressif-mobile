package com.nikitakrapo.progressif.auth.ui

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.nikitakrapo.progressif.auth.ui.AuthenticationComponent.Child
import com.nikitakrapo.progressif.auth.ui.landing.AuthLandingComponentImpl
import com.nikitakrapo.progressif.auth.ui.signin.SignInComponentImpl
import com.nikitakrapo.progressif.auth.ui.signup.SignUpComponentImpl
import com.nikitakrapo.progressif.decompose.asStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.serialization.Serializable

class AuthenticationComponentImpl(
    componentContext: ComponentContext,
) : AuthenticationComponent, ComponentContext by componentContext {

    private val navigation = StackNavigation<Configuration>()

    override val child: StateFlow<ChildStack<*, Child>> = childStack(
        key = "AuthenticationComponent",
        source = navigation,
        serializer = Configuration.serializer(),
        initialConfiguration = Configuration.Landing,
        handleBackButton = true,
        childFactory = ::createChild,
    ).asStateFlow()

    private fun createChild(config: Configuration, componentContext: ComponentContext): Child =
        when (config) {
            Configuration.Landing -> Child.Landing(AuthLandingComponentImpl(componentContext))
            Configuration.SignIn -> Child.SignIn(SignInComponentImpl(componentContext))
            Configuration.SignUp -> Child.SignUp(SignUpComponentImpl(componentContext))
        }

    @Serializable
    private sealed interface Configuration {

        @Serializable
        data object Landing : Configuration

        @Serializable
        data object SignIn : Configuration

        @Serializable
        data object SignUp : Configuration

    }
}
