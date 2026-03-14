package com.nikitakrapo.progressif.design.components.buttons

import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.nikitakrapo.progressif.design.theme.PreviewTheme

@Composable
fun MediumButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    FilledTonalButton(
        content = {
            Text(text = text)
        },
        onClick = onClick,
        modifier = modifier,
    )
}

@Preview
@Composable
private fun MediumButtonPreview() {
    PreviewTheme {
        MediumButton(
            text = "Press me!",
            onClick = {},
        )
    }
}