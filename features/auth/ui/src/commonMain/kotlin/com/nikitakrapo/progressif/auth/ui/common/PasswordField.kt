package com.nikitakrapo.progressif.auth.ui.common

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.nikitakrapo.progressf.strings.Res
import com.nikitakrapo.progressf.strings.common_password
import com.nikitakrapo.progressif.design.components.textfield.TextField
import com.nikitakrapo.progressif.design.components.textfield.TrailingItem
import com.nikitakrapo.progressif.design.icon.icons
import com.nikitakrapo.progressif.design.theme.ProgressifTheme
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun PasswordField(
    password: String,
    onPasswordChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    error: String? = null,
    imeAction: ImeAction = ImeAction.Next,
) {
    var passwordMasked by remember { mutableStateOf(false) }
    TextField(
        value = password,
        onValueChange = onPasswordChange,
        label = stringResource(Res.string.common_password),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = imeAction,
        ),
        visualTransformation = if (passwordMasked) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        },
        trailingItem = TrailingItem.Icon(
            painter = painterResource(
                if (passwordMasked) {
                    ProgressifTheme.icons.visibilityOff
                } else {
                    ProgressifTheme.icons.visibility
                }
            ),
            onClick = { passwordMasked = !passwordMasked },
            contentDescription = null,
        ),
        isError = error != null,
        supportingText = error,
        singleLine = true,
        modifier = modifier,
    )
}