package com.nikitakrapo.progressif.auth.ui.landing

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import com.nikitakrapo.progressf.strings.Res
import com.nikitakrapo.progressf.strings.auth_landing_create_account_button
import com.nikitakrapo.progressf.strings.auth_landing_sign_in_action
import com.nikitakrapo.progressf.strings.auth_landing_sign_in_prompt
import com.nikitakrapo.progressif.design.components.buttons.LargeButton
import com.nikitakrapo.progressif.design.theme.ProgressifTheme
import com.nikitakrapo.progressif.design.theme.spacing
import com.nikitakrapo.progressif.design.utils.annotatedstring.buildAnnotatedString
import org.jetbrains.compose.resources.stringResource

@Composable
fun AuthLandingScreen(
    component: AuthLandingComponent,
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        LargeButton(
            onClick = component::onRegisterClick,
            text = stringResource(Res.string.auth_landing_create_account_button),
        )

        Spacer(modifier = Modifier.height(ProgressifTheme.spacing.vertical.buttonToText))

        val loginLabel = buildAnnotatedString(
            stringResource(Res.string.auth_landing_sign_in_prompt) to null,
            " " to null,
            stringResource(Res.string.auth_landing_sign_in_action) to SpanStyle(fontWeight = FontWeight.Bold)
        )
        Text(
            text = loginLabel,
            modifier = Modifier
                .clickable(onClick = component::onLoginClick),
        )
    }
}
