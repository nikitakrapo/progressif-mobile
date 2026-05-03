package com.nikitakrapo.progressif.auth.ui.common

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.nikitakrapo.progressif.design.icon.icons
import com.nikitakrapo.progressif.design.theme.ProgressifTheme
import org.jetbrains.compose.resources.painterResource

@Composable
fun EmailPasswordField(
    email: String,
    onEmailChange: (String) -> Unit,
    password: String,
    onPasswordChange: (String) -> Unit,
) {
    TextField(
        value = email,
        onValueChange = onEmailChange,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next,
        ),
        singleLine = true,
    )

    var passwordMasked by remember { mutableStateOf(false) }
    TextField(
        value = password,
        onValueChange = onPasswordChange,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done,
        ),
        visualTransformation = if (passwordMasked) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        },
        trailingIcon = {
            val icon = if (passwordMasked) {
                ProgressifTheme.icons.visibilityOff
            } else {
                ProgressifTheme.icons.visibility
            }
            IconButton(
                onClick = { passwordMasked = !passwordMasked },
                modifier = Modifier
                    .semantics(mergeDescendants = false) {},
            ) {
                Icon(
                    painter = painterResource(icon),
                    contentDescription = null,
                )
            }
        },
        singleLine = true,
    )
}
