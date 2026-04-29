package com.nikitakrapo.progressif.auth.ui.landing

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun AuthLandingScreen(
    component: AuthLandingComponent,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = "Auth Landing")
        Button(
            onClick = component::onLoginClick,
            content = {
                Text(text = "To login")
            },
        )
        Button(
            onClick = component::onRegisterClick,
            content = {
                Text(text = "To registration")
            },
        )

    }
}
