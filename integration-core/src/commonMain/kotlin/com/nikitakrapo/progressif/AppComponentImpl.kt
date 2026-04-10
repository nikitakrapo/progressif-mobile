package com.nikitakrapo.progressif

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.decompose.router.stack.childStack
import com.nikitakrapo.progressif.decompose.asStateFlow
import com.nikitakrapo.progressif.di.Di
import com.nikitakrapo.progressif.profile.ProfileComponentImpl
import com.nikitakrapo.progressif.progressions_list.ProgressionsListComponentImpl
import kotlinx.coroutines.flow.StateFlow
import kotlinx.serialization.Serializable

class AppComponentImpl(
    componentContext: ComponentContext,
) : AppComponent, ComponentContext by componentContext {

    private val navigation = StackNavigation<Configuration>()

    override val child: StateFlow<ChildStack<*, AppComponent.Child>>
        = childStack(
            source = navigation,
            serializer = Configuration.serializer(),
            initialConfiguration = Configuration.ProgressionsList,
            handleBackButton = true,
            childFactory = ::createChild,
        ).asStateFlow()

    override fun onProgressionsClick() {
        navigation.bringToFront(Configuration.ProgressionsList)
    }

    override fun onProfileClick() {
        navigation.bringToFront(Configuration.Profile)
    }

    private fun createChild(config: Configuration, componentContext: ComponentContext): AppComponent.Child =
        when (config) {
            Configuration.ProgressionsList -> AppComponent.Child.ProgressionsList(
                ProgressionsListComponentImpl(
                    componentContext = componentContext,
                    storeFactory = Di.get(),
                    progressionsRepository = Di.get(),
                )
            )
            Configuration.Profile -> AppComponent.Child.Profile(
                ProfileComponentImpl(
                    componentContext = componentContext,
                    storeFactory = Di.get(),
                )
            )
        }

    @Serializable
    private sealed interface Configuration {

        @Serializable
        data object ProgressionsList : Configuration

        @Serializable
        data object Profile : Configuration
    }
}
