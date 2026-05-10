package com.nikitakrapo.progressif.design.components.screen

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.nikitakrapo.progressif.design.theme.ProgressifTheme
import com.nikitakrapo.progressif.design.theme.spacing
import com.nikitakrapo.progressif.design.utils.padding.plus

@Composable
fun ScreenScaffold(
    topBar: @Composable () -> Unit = {},
    snackbarHostState: SnackbarHostState? = null,
    modifier: Modifier = Modifier,
    content: @Composable (PaddingValues) -> Unit,
) {
    Scaffold(
        topBar = topBar,
        snackbarHost = {
            snackbarHostState?.let {
                SnackbarHost(hostState = it)
            }
        },
        modifier = modifier,
        content = { content(it + PaddingValues(ProgressifTheme.spacing.screen)) },
    )
}