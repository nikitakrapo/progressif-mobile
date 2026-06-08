package com.nikitakrapo.progressif.onboarding.ui

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import com.nikitakrapo.progressf.strings.Res
import com.nikitakrapo.progressf.strings.common_username
import com.nikitakrapo.progressif.design.components.textfield.TextField
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun UsernameField(
    username: String,
    onUsernameChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    imeAction: ImeAction = ImeAction.Next,
) {
    TextField(
        value = username,
        onValueChange = onUsernameChange,
        label = stringResource(Res.string.common_username),
        keyboardOptions = KeyboardOptions(
            imeAction = imeAction,
        ),
        singleLine = true,
        modifier = modifier,
    )
}