package com.nikitakrapo.progressif.auth.ui.registration

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import com.nikitakrapo.progressf.strings.Res
import com.nikitakrapo.progressf.strings.registration_submit_button_text
import com.nikitakrapo.progressif.auth.ui.common.EmailField
import com.nikitakrapo.progressif.auth.ui.common.PasswordField
import com.nikitakrapo.progressif.auth.ui.common.UsernameField
import com.nikitakrapo.progressif.design.components.appbar.NavigationButtonConfig
import com.nikitakrapo.progressif.design.components.appbar.TopAppBar
import com.nikitakrapo.progressif.design.components.buttons.LargeButton
import com.nikitakrapo.progressif.design.components.screen.ScreenScaffold
import com.nikitakrapo.progressif.design.theme.PreviewTheme
import com.nikitakrapo.progressif.design.theme.ProgressifTheme
import com.nikitakrapo.progressif.design.theme.spacing
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
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
                UsernameField(
                    username = state.username,
                    onUsernameChange = component::onUsernameChange,
                    modifier = Modifier
                        .fillMaxWidth(),
                )

                Spacer(modifier = Modifier.height(ProgressifTheme.spacing.betweenComponents))

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

                state.error?.let { error ->
                    Text(
                        text = error.toMessage(),
                    )
                }

                Spacer(modifier = Modifier.height(ProgressifTheme.spacing.componentToButton))

                LargeButton(
                    onClick = component::onSubmitClicked,
                    text = stringResource(Res.string.registration_submit_button_text),
                    enabled = state.submitButtonEnabled,
                )
            }
        },
    )
}

@Preview
@Composable
private fun RegistrationScreenPreview() {
    PreviewTheme {
        RegistrationScreen(
            component = PreviewRegistrationComponent(),
        )
    }
}

private fun PreviewRegistrationComponent() = object : RegistrationComponent {
    override val state: StateFlow<RegistrationState> = MutableStateFlow(
        RegistrationState(
            username = "kickflip",
            email = "cool@email.com",
            password = "password123",
            isLoading = false,
            error = null,
        )
    )

    override fun onUsernameChange(value: String) {}

    override fun onEmailChange(value: String) {}

    override fun onPasswordChange(value: String) {}

    override fun onSubmitClicked() {}

    override fun onBackClick() {}
}
