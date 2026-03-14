package com.nikitakrapo.progressif.design.components.errors

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.nikitakrapo.progressf.strings.Res
import com.nikitakrapo.progressf.strings.error_unknown_message
import com.nikitakrapo.progressf.strings.refresh_button_text
import com.nikitakrapo.progressif.design.components.buttons.MediumButton
import com.nikitakrapo.progressif.design.theme.PreviewTheme
import com.nikitakrapo.progressif.design.theme.ProgressifTheme
import com.nikitakrapo.progressif.design.theme.spacing
import org.jetbrains.compose.resources.stringResource

@Composable
fun ErrorContent(
    text: String = stringResource(Res.string.error_unknown_message),
    onRefreshClicked: (() -> Unit)? = null,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize(),
    ) {
        Text(
            text = text,
            style = ProgressifTheme.typography.titleLarge,
        )

        Spacer(modifier = Modifier.height(ProgressifTheme.spacing.betweenComponents))

        onRefreshClicked?.let {
            MediumButton(
                text = stringResource(Res.string.refresh_button_text),
                onClick = it,
            )
        }
    }
}

@Preview
@Composable
private fun ErrorContentPreview() {
    PreviewTheme {
        ErrorContent(
            text = "Error!! Please fix",
            onRefreshClicked = {},
        )
    }
}