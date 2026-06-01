package com.nikitakrapo.progressif.tricks

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.nikitakrapo.progressif.design.components.bottombar.BottomBarPadding
import com.nikitakrapo.progressif.design.components.errors.ErrorContent
import com.nikitakrapo.progressif.design.components.loading.LinearProgressBar
import com.nikitakrapo.progressif.design.theme.ProgressifTheme
import com.nikitakrapo.progressif.design.theme.spacing
import com.nikitakrapo.progressif.design.utils.padding.plus

@Composable
fun TricksScreen(
    component: TricksComponent,
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
                contentPadding = PaddingValues(ProgressifTheme.spacing.screen) + BottomBarPadding()
            ) {
                items(state.tricks) { trick ->
                    ListItem(
                        headlineContent = {
                            Text(
                                text = trick.name,
                            )
                        },
                    )
                    Spacer(modifier = Modifier.height(ProgressifTheme.spacing.betweenComponents))
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
