package com.nikitakrapo.progressif

import com.arkivanov.decompose.router.stack.ChildStack
import com.nikitakrapo.progressif.progressions_list.ProgressionsListComponent
import kotlinx.coroutines.flow.StateFlow

interface AppComponent {

    val child: StateFlow<ChildStack<*, Child>>

    sealed interface Child {

        data class ProgressionsList(val component: ProgressionsListComponent) : Child
    }
}