package com.nikitakrapo.progressif

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.nikitakrapo.progressf.strings.bottom_bar_item_profile
import com.nikitakrapo.progressf.strings.bottom_bar_item_progression
import com.nikitakrapo.progressif.auth.ui.AuthenticationScreen
import com.nikitakrapo.progressif.design.components.bottombar.BottomBar
import com.nikitakrapo.progressif.design.components.bottombar.BottomBarItem
import com.nikitakrapo.progressif.design.icon.icons
import com.nikitakrapo.progressif.design.theme.ProgressifTheme
import com.nikitakrapo.progressif.profile.ui.ProfileScreen
import com.nikitakrapo.progressif.progressions_list.ProgressionsListScreen
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import com.nikitakrapo.progressf.strings.Res as StringRes

@Composable
fun App(
    component: AppComponent,
) {
    ProgressifTheme {
        Surface(
            color = ProgressifTheme.colorScheme.background,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
            ) {
                val child by component.child.collectAsState()

                Children(
                    stack = child,
                    content = { createdChild ->
                        when (val instance = createdChild.instance) {
                            is AppComponent.Child.ProgressionsList -> ProgressionsListScreen(
                                instance.component,
                            )

                            is AppComponent.Child.Profile -> ProfileScreen(
                                instance.component,
                            )

                            is AppComponent.Child.Authentication -> AuthenticationScreen(
                                instance.component,
                            )
                        }
                    },
                    modifier = Modifier
                        .weight(1f),
                )

                if (child.active.instance.showBottomBar) {
                    BottomBar(
                        items = listOf(
                            BottomBarItem(
                                icon = painterResource(ProgressifTheme.icons.progressions),
                                title = stringResource(StringRes.string.bottom_bar_item_progression),
                                onClick = component::onProgressionsClick,
                                selected = child.active.instance is AppComponent.Child.ProgressionsList,
                            ),
                            BottomBarItem(
                                icon = painterResource(ProgressifTheme.icons.profile),
                                title = stringResource(StringRes.string.bottom_bar_item_profile),
                                onClick = component::onProfileClick,
                                selected = child.active.instance is AppComponent.Child.Profile,
                            ),
                        ),
                    )
                }
            }
        }
    }
}

private val AppComponent.Child.showBottomBar: Boolean get() = when (this) {
    is AppComponent.Child.Authentication -> false
    is AppComponent.Child.Profile,
    is AppComponent.Child.ProgressionsList -> true
}
