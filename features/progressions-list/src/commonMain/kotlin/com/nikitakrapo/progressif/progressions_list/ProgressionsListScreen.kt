package com.nikitakrapo.progressif.progressions_list

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.nikitakrapo.progressf.strings.Res
import com.nikitakrapo.progressf.strings.progressions_list_add_progression
import com.nikitakrapo.progressif.design.components.bottombar.BottomBarPadding
import com.nikitakrapo.progressif.design.components.errors.ErrorContent
import com.nikitakrapo.progressif.design.components.loading.LinearProgressBar
import com.nikitakrapo.progressif.design.theme.ProgressifTheme
import com.nikitakrapo.progressif.design.theme.spacing
import com.nikitakrapo.progressif.design.utils.padding.plus
import org.jetbrains.compose.resources.stringResource

@Composable
fun ProgressionsListScreen(
    component: ProgressionsListComponent,
) {
    val state by component.state.collectAsStateWithLifecycle()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(ProgressifTheme.colorScheme.surfaceVariant)
            .systemBarsPadding(),
    ) {
        PullToRefreshBox(
            isRefreshing = false,
            onRefresh = component::onRefresh,
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
                contentPadding = ProgressifTheme.spacing.screen + BottomBarPadding()
            ) {
                itemsIndexed(state.items) { index, item ->
                    when (item) {
                        is ProgressionsListItem.ProgressionItem -> {
                            ListItem(
                                headlineContent = {
                                    Text(
                                        text = item.progression.name,
                                    )
                                },
                                modifier = Modifier
                                    .clickable { component.onProgressionClick(index) },
                            )
                        }
                        ProgressionsListItem.AddProgressionItem -> {
                            ListItem(
                                headlineContent = {
                                    Text(
                                        text = stringResource(Res.string.progressions_list_add_progression),
                                    )
                                },
                                modifier = Modifier
                                    .clickable { component.onAddProgressionClick() },
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(ProgressifTheme.spacing.vertical.betweenComponents))
                }
            }
        }

        AnimatedVisibility(
            visible = state.errorText != null,
            enter = fadeIn(),
            exit = fadeOut(),
        ) {
            state.errorText?.let { errorText ->
                ErrorContent(
                    text = errorText.resolve(),
                )
            }
        }

        AnimatedVisibility(
            visible = state.isLoading,
            enter = fadeIn(),
            exit = fadeOut(),
        ) {
            LinearProgressBar(modifier = Modifier.fillMaxWidth())
        }
    }
}
