package com.nikitakrapo.progressif

import com.arkivanov.decompose.router.stack.ChildStack
import com.nikitakrapo.progressif.auth.ui.AuthenticationComponent
import com.nikitakrapo.progressif.profile.ProfileComponent
import com.nikitakrapo.progressif.progressions_list.ProgressionsListComponent
import com.nikitakrapo.progressif.tricks.TricksComponent
import kotlinx.coroutines.flow.StateFlow

interface AppComponent {

    val child: StateFlow<ChildStack<*, Child>>

    fun onProgressionsClick()

    fun onTricksClick()

    fun onProfileClick()

    sealed interface Child {

        data class ProgressionsList(val component: ProgressionsListComponent) : Child

        data class Tricks(val component: TricksComponent) : Child

        data class Profile(val component: ProfileComponent) : Child

        data class Authentication(val component: AuthenticationComponent) : Child
    }
}
