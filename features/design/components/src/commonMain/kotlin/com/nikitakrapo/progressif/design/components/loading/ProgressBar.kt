@file:OptIn(ExperimentalMaterial3ExpressiveApi::class)

package com.nikitakrapo.progressif.design.components.loading

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.LinearWavyProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.nikitakrapo.progressif.design.theme.PreviewTheme
import com.nikitakrapo.progressif.design.theme.ProgressifTheme
import com.nikitakrapo.progressif.design.theme.spacing

@Composable
fun LinearProgressBar(
    modifier: Modifier = Modifier,
) {
    LinearWavyProgressIndicator(
        modifier = modifier
            .padding(ProgressifTheme.spacing.screen),
    )
}

@Preview
@Composable
private fun LinearProgressBarPreview() {
    PreviewTheme {
        LinearProgressBar()
    }
}