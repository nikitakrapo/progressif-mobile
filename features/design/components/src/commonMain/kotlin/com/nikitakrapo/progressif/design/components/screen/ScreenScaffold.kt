package com.nikitakrapo.progressif.design.components.screen

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable

// is a wrapper bc I'll probably need to customize it later
@Composable
fun ScreenScaffold(
    topBar: @Composable () -> Unit,
    content: @Composable (PaddingValues) -> Unit,
) {
    Scaffold(
        topBar = topBar,
        content = content,
    )
}