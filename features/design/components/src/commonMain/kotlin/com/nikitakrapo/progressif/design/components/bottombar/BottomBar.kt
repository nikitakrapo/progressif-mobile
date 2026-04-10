package com.nikitakrapo.progressif.design.components.bottombar

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.tooling.preview.Preview
import com.nikitakrapo.progressif.design.icon.icons
import com.nikitakrapo.progressif.design.theme.PreviewTheme
import com.nikitakrapo.progressif.design.theme.ProgressifTheme
import org.jetbrains.compose.resources.painterResource

@Stable
data class BottomBarItem(
    val icon: Painter,
    val title: String,
    val onClick: () -> Unit,
    val selected: Boolean,
)

@Composable
fun BottomBar(
    items: List<BottomBarItem>,
    modifier: Modifier = Modifier,
) {
    NavigationBar(
        content = {
            items.forEach { item ->
                NavigationBarItem(
                    selected = item.selected,
                    onClick = item.onClick,
                    icon = {
                        Icon(
                            painter = item.icon,
                            contentDescription = item.title,
                        )
                    },
                    label = {
                        Text(text = item.title)
                    },
                    alwaysShowLabel = false,
                )
            }
        },
        modifier = modifier,
    )
}

@Preview
@Composable
private fun BottomBarPreview() {
    PreviewTheme {
        BottomBar(
            items = listOf(
                BottomBarItem(
                    icon = painterResource(ProgressifTheme.icons.cross),
                    title = "Item 1",
                    onClick = {},
                    selected = false,
                ),
                BottomBarItem(
                    icon = painterResource(ProgressifTheme.icons.cross),
                    title = "Item 2",
                    onClick = {},
                    selected = true,
                ),
                BottomBarItem(
                    icon = painterResource(ProgressifTheme.icons.cross),
                    title = "Item 3",
                    onClick = {},
                    selected = false,
                ),
            )
        )
    }
}