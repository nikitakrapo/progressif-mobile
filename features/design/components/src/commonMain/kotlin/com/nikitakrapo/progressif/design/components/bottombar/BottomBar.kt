package com.nikitakrapo.progressif.design.components.bottombar

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nikitakrapo.progressif.design.icon.icons
import com.nikitakrapo.progressif.design.theme.PreviewTheme
import com.nikitakrapo.progressif.design.theme.ProgressifTheme
import org.jetbrains.compose.resources.painterResource

private const val USE_FLOATING_BOTTOM_BAR = false

fun BottomBarPadding() = PaddingValues(bottom = 90.dp)

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
    if (USE_FLOATING_BOTTOM_BAR) {
        Box(
            modifier = modifier
                .padding(16.dp)
                .navigationBarsPadding(),
        ) {
            Surface(
                shape = CircleShape,
                shadowElevation = 16.dp,
            ) {
                Row {
                    items.forEach { item ->
                        BottomBarItem(
                            item = item,
                        )
                    }
                }
            }
        }
    } else {
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
}

@Composable
private fun RowScope.BottomBarItem(
    item: BottomBarItem,
    colors: NavigationBarItemColors = NavigationBarItemDefaults.colors(
        selectedTextColor = NavigationBarItemDefaults.colors().unselectedTextColor,
        unselectedTextColor = NavigationBarItemDefaults.colors().selectedTextColor,
    ),
) {
    val interactionSource = remember { MutableInteractionSource() }

    val iconColor by animateColorAsState(
        targetValue = if (item.selected) colors.selectedIconColor else colors.unselectedIconColor,
    )

    val textColor by animateColorAsState(
        targetValue = if (item.selected) colors.selectedTextColor else colors.unselectedTextColor,
    )

    val indicationColor by animateColorAsState(
        targetValue = if (item.selected) colors.selectedIndicatorColor else colors.selectedIndicatorColor.copy(alpha = 0f),
    )

    Box(
        modifier = Modifier
            .selectable(
                selected = item.selected,
                interactionSource = interactionSource,
                role = Role.Tab,
                onClick = item.onClick,
                indication = null,
            )
            .weight(1f, fill = false)
            .padding(6.dp),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            modifier = Modifier
                .background(
                    color = indicationColor,
                    shape = CircleShape,
                )
                .padding(
                    vertical = 6.dp,
                    horizontal = 24.dp,
                ),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Icon(
                painter = item.icon,
                contentDescription = item.title,
                tint = iconColor,
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = item.title,
                style = ProgressifTheme.typography.labelMedium,
                color = textColor,
            )
        }
    }
}

@Preview
@Composable
private fun BottomBarPreview() {
    PreviewTheme {
        var selectedIndex by remember { mutableStateOf(0) }
        BottomBar(
            items = listOf(
                BottomBarItem(
                    icon = painterResource(ProgressifTheme.icons.cross),
                    title = "Item 0",
                    onClick = { selectedIndex = 0 },
                    selected = selectedIndex == 0,
                ),
                BottomBarItem(
                    icon = painterResource(ProgressifTheme.icons.profile),
                    title = "Item 1",
                    onClick = { selectedIndex = 1 },
                    selected = selectedIndex == 1,
                ),
                BottomBarItem(
                    icon = painterResource(ProgressifTheme.icons.skateboarding),
                    title = "Item 2",
                    onClick = { selectedIndex = 2 },
                    selected = selectedIndex == 2,
                ),
            )
        )
    }
}