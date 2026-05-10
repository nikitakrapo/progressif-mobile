package com.nikitakrapo.progressif.design.components.buttons

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nikitakrapo.progressif.design.theme.PreviewTheme
import com.nikitakrapo.progressif.design.theme.ProgressifTheme

internal object LargeButtonDefaults {

    val VerticalSpacing = 16.dp
    val HorizontalSpacing = 32.dp
    val MinWidth = 300.dp
}

@Composable
fun LargeButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Button(
        content = {
            Text(
                text = text,
                style = ProgressifTheme.typography.headlineSmall,
            )
        },
        onClick = onClick,
        shape = ProgressifTheme.shapes.large,
        contentPadding = PaddingValues(
            vertical = LargeButtonDefaults.VerticalSpacing,
            horizontal = LargeButtonDefaults.HorizontalSpacing,
        ),
        modifier = modifier
            .sizeIn(minWidth = LargeButtonDefaults.MinWidth),
    )
}

@Preview
@Composable
private fun LargeButtonPreview() {
    PreviewTheme {
        MediumButton(
            text = "Press me!",
            onClick = {},
        )
    }
}