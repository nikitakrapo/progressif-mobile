package com.nikitakrapo.progressif.auth.ui.signin

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun SignInScreen(
    component: SignInComponent,
) {
    val state by component.state.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(text = "Sign In")
        TextField(
            value = state.email,
            onValueChange = component::onEmailChanged,
        )
        TextField(
            value = state.password,
            onValueChange = component::onPasswordChanged,
        )
        Button(
            onClick = component::onSubmitClicked,
            content = {
                Text(text = "Sign in")
            }
        )
    }
}
