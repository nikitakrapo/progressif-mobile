package com.nikitakrapo.progressif.auth.ui

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.nikitakrapo.progressif.auth.ui.AuthenticationComponent.Child
import com.nikitakrapo.progressif.auth.ui.landing.AuthLandingComponentImpl
import com.nikitakrapo.progressif.auth.ui.login.LoginComponentImpl
import com.nikitakrapo.progressif.auth.ui.registration.RegistrationComponentImpl
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
            Configuration.Landing -> {
                val component = AuthLandingComponentImpl(
                    componentContext = componentContext,
                    openLogin = { navigation.bringToFront(Configuration.Login) },
                    openRegistration = { navigation.bringToFront(Configuration.Registration) },
                )
                Child.Landing(component)
            }
            Configuration.Login -> {
                val component = LoginComponentImpl(
                    componentContext = componentContext,
                    navigateBack = { navigation.pop() },
                )
                Child.Login(component)
            }
            Configuration.Registration -> {
                val component = RegistrationComponentImpl(
                    componentContext = componentContext,
                    navigateBack = { navigation.pop() },
                )
                Child.Registration(component)
            }
        }

    @Serializable
    private sealed interface Configuration {

        @Serializable
        data object Landing : Configuration

        @Serializable
        data object Login : Configuration

        @Serializable
        data object Registration : Configuration

    }
}
