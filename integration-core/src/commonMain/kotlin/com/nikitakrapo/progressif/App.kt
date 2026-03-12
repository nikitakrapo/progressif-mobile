package com.nikitakrapo.progressif

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.nikitakrapo.progressif.design.theme.ProgressifTheme
import com.nikitakrapo.progressif.progressions_list.ProgressionsList

@Composable
fun App(
    component: AppComponent,
) {
    ProgressifTheme {
        Surface(color = ProgressifTheme.colorScheme.background) {

            val child by component.child.collectAsState()
            Children(
                stack = child,
                content = { createdChild ->
                    when (val instance = createdChild.instance) {
                        is AppComponent.Child.ProgressionsList -> ProgressionsList(instance.component)
                    }
                },
            )
        }
    }
}