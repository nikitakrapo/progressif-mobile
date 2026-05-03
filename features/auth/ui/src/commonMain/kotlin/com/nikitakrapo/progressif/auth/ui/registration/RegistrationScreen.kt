package com.nikitakrapo.progressif.auth.ui.registration

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.nikitakrapo.progressf.strings.Res
import com.nikitakrapo.progressf.strings.registration_submit_button_text
import com.nikitakrapo.progressif.auth.ui.common.EmailPasswordField
import com.nikitakrapo.progressif.design.components.appbar.NavigationButtonConfig
import com.nikitakrapo.progressif.design.components.appbar.TopAppBar
import com.nikitakrapo.progressif.design.components.screen.ScreenScaffold
import org.jetbrains.compose.resources.stringResource

@Composable
fun RegistrationScreen(
    component: RegistrationComponent,
) {
    val state by component.state.collectAsState()

    ScreenScaffold(
        topBar = {
            TopAppBar(
                navigation = NavigationButtonConfig.Back(onClick = component::onBackClick),
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .imePadding(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                EmailPasswordField(
                    email = state.email,
                    onEmailChange = component::onEmailChanged,
                    password = state.password,
                    onPasswordChange = component::onPasswordChanged,
                )
                state.error?.let { error ->
                    Text(
                        text = error.toMessage(),
                    )
                }
                Button(
                    onClick = component::onSubmitClicked,
                    enabled = state.submitButtonEnabled,
                    content = {
                        Text(
                            text = stringResource(Res.string.registration_submit_button_text),
                        )
                    },
                )
            }
        },
    )
}
