package com.nikitakrapo.progressif.auth.ui.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import com.nikitakrapo.progressif.auth.ui.common.EmailField
import com.nikitakrapo.progressif.auth.ui.common.PasswordField
import com.nikitakrapo.progressif.design.theme.ProgressifTheme
import com.nikitakrapo.progressif.design.theme.spacing

@Composable
fun LoginScreen(
    component: LoginComponent,
) {
    val state by component.state.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        EmailField(
            email = state.email,
            onEmailChange = component::onEmailChange,
            modifier = Modifier
                .fillMaxWidth(),
        )

        Spacer(modifier = Modifier.height(ProgressifTheme.spacing.betweenComponents))

        PasswordField(
            password = state.password,
            onPasswordChange = component::onPasswordChange,
            imeAction = ImeAction.Done,
            modifier = Modifier
                .fillMaxWidth(),
        )

        Spacer(modifier = Modifier.height(ProgressifTheme.spacing.componentToButton))

        Button(
            onClick = component::onSubmitClicked,
            content = {
                Text(text = "Log in")
            }
        )
    }
}
