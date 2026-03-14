@file:OptIn(ExperimentalMaterial3Api::class)

package com.nikitakrapo.progressif.design.components.appbar

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.nikitakrapo.progressf.design.core.arrow_back_24
import com.nikitakrapo.progressf.design.core.close_24
import com.nikitakrapo.progressf.strings.close_screen_content_description
import com.nikitakrapo.progressf.strings.go_back_content_description
import com.nikitakrapo.progressif.design.icon.Icon
import com.nikitakrapo.progressif.design.theme.PreviewTheme
import com.nikitakrapo.progressif.strings.Text
import androidx.compose.material3.TopAppBar as TopAppBarM3
import com.nikitakrapo.progressf.design.core.Res as IconRes
import com.nikitakrapo.progressf.strings.Res as StringRes

@Composable
fun TopAppBar(
    navigation: NavigationButtonConfig? = null,
    actions: List<ActionButtonConfig> = emptyList(),
) {
    TopAppBarM3(
        title = {},
        navigationIcon = {
            navigation?.let {
                IconButton(
                    onClick = it.onClick,
                    content = {
                        Icon(
                            painter = it.icon.resolve(),
                            contentDescription = it.contentDescription.resolve(),
                        )
                    },
                )
            }
        },
        actions = {
            actions.forEach { config ->
                ActionButton(
                    config = config,
                )
            }
        },
    )
}

@Composable
private fun ActionButton(
    config: ActionButtonConfig,
) {
    when (config) {
        is ActionButtonConfig.IconButton -> IconButton(
            onClick = config.onClick,
            content = {
                Icon(
                    painter = config.icon.resolve(),
                    contentDescription = config.contentDescription.resolve(),
                )
            },
        )

    }
}

sealed interface NavigationButtonConfig {

    val onClick: () -> Unit

    val icon: Icon

    val contentDescription: Text

    data class Back(
        override val onClick: () -> Unit,
    ) : NavigationButtonConfig {

        override val icon = Icon.DrawableRes(IconRes.drawable.arrow_back_24)

        override val contentDescription = Text.StringRes(StringRes.string.go_back_content_description)
    }

    data class Close(
        override val onClick: () -> Unit,
    ) : NavigationButtonConfig {

        override val icon = Icon.DrawableRes(IconRes.drawable.close_24)

        override val contentDescription = Text.StringRes(StringRes.string.close_screen_content_description)
    }
}

sealed interface ActionButtonConfig {

    data class IconButton(
        val onClick: () -> Unit,
        val icon: Icon,
        val contentDescription: Text,
    ) : ActionButtonConfig
}

@Preview
@Composable
fun TopAppBarPreview() {
    PreviewTheme {
        TopAppBar(
            navigation = NavigationButtonConfig.Back(onClick = {})
        )
    }
}