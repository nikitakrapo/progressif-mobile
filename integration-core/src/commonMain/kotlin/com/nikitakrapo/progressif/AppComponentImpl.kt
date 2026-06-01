package com.nikitakrapo.progressif

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.navigate
import com.arkivanov.essenty.lifecycle.coroutines.coroutineScope
import com.nikitakrapo.progressif.auth.ui.AuthenticationComponentImpl
import com.nikitakrapo.progressif.auth.user.AuthState
import com.nikitakrapo.progressif.auth.user.UserRepository
import com.nikitakrapo.progressif.decompose.asStateFlow
import com.nikitakrapo.progressif.di.Di
import com.nikitakrapo.progressif.profile.ProfileComponentImpl
import com.nikitakrapo.progressif.progressions_list.ProgressionsListComponentImpl
import com.nikitakrapo.progressif.tricks.TricksComponentImpl
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable

class AppComponentImpl(
    componentContext: ComponentContext,
    private val userRepository: UserRepository = Di.get(),

) : AppComponent, ComponentContext by componentContext {

    private val scope = coroutineScope()
    private val navigation = StackNavigation<Configuration>()

    init {
        scope.launch {
            userRepository.state.collect(::handleAuthStateChanged)
        }
    }

    override val child: StateFlow<ChildStack<*, AppComponent.Child>> = childStack(
        key = "AppComponent",
        source = navigation,
        serializer = Configuration.serializer(),
        initialConfiguration = userRepository.state.value.toRootConfiguration(),
        handleBackButton = true,
        childFactory = ::createChild,
    ).asStateFlow()

    override fun onProgressionsClick() {
        navigation.bringToFront(Configuration.ProgressionsList)
    }

    override fun onTricksClick() {
        navigation.bringToFront(Configuration.Tricks)
    }

    override fun onProfileClick() {
        navigation.bringToFront(Configuration.Profile)
    }

    private fun handleAuthStateChanged(state: AuthState) {
        navigation.navigate { listOf(state.toRootConfiguration()) }
    }

    private fun AuthState.toRootConfiguration(): Configuration = when (this) {
        is AuthState.SignedIn -> Configuration.ProgressionsList
        else -> Configuration.Authentication
    }

    private fun createChild(
        config: Configuration,
        componentContext: ComponentContext
    ): AppComponent.Child =
        when (config) {
            Configuration.ProgressionsList -> AppComponent.Child.ProgressionsList(
                ProgressionsListComponentImpl(
                    componentContext = componentContext,
                    storeFactory = Di.get(),
                    progressionsRepository = Di.get(),
                )
            )

            Configuration.Tricks -> AppComponent.Child.Tricks(
                TricksComponentImpl(
                    componentContext = componentContext,
                    storeFactory = Di.get(),
                    tricksRepository = Di.get(),
                )
            )

            Configuration.Profile -> AppComponent.Child.Profile(
                ProfileComponentImpl(
                    componentContext = componentContext,
                    storeFactory = Di.get(),
                    userRepository = Di.get(),
                )
            )

            Configuration.Authentication -> AppComponent.Child.Authentication(
                AuthenticationComponentImpl(
                    componentContext = componentContext,
                )
            )
        }

    @Serializable
    private sealed interface Configuration {

        @Serializable
        data object ProgressionsList : Configuration

        @Serializable
        data object Tricks : Configuration

        @Serializable
        data object Profile : Configuration

        @Serializable
        data object Authentication : Configuration
    }
}
