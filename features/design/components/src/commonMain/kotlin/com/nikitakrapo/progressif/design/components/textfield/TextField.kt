package com.nikitakrapo.progressif.design.components.textfield

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

internal object TextFieldTokens {

    val MaxWidth = 360.dp
}

sealed interface TrailingItem {

    data class Icon(
        val painter: Painter,
        val onClick: () -> Unit,
        val contentDescription: String?,
    ) : TrailingItem
}

@Composable
fun TextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String? = null,
    prefix: String? = null,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    isError: Boolean = false,
    trailingItem: TrailingItem? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = false,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    interactionSource: MutableInteractionSource? = null
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .widthIn(max = TextFieldTokens.MaxWidth),
        label = label?.let { { Text(it) } },
        prefix = prefix?.let { { Text(it) } },
        enabled = enabled,
        readOnly = readOnly,
        isError = isError,
        trailingIcon = trailingItem?.render(),
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        singleLine = singleLine,
        maxLines = maxLines,
        minLines = minLines,
        interactionSource = interactionSource,
    )
}

@Composable
private fun TrailingItem.render(): @Composable () -> Unit = {
    when (this) {
        is TrailingItem.Icon -> {
            IconButton(
                onClick = onClick,
            ) {
                Icon(
                    painter = painter,
                    contentDescription = contentDescription,
                )
            }
        }
    }
}
