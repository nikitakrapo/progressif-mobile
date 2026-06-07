package com.nikitakrapo.progressif.auth.ui.registration

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import com.nikitakrapo.progressf.strings.Res
import com.nikitakrapo.progressf.strings.registration_already_registered_cta
import com.nikitakrapo.progressf.strings.registration_already_registered_sign_in_label
import com.nikitakrapo.progressf.strings.registration_submit_button_text
import com.nikitakrapo.progressf.strings.registration_title
import com.nikitakrapo.progressif.auth.ui.common.AuthUiTokens
import com.nikitakrapo.progressif.auth.ui.common.EmailField
import com.nikitakrapo.progressif.auth.ui.common.PasswordField
import com.nikitakrapo.progressif.design.components.appbar.NavigationButtonConfig
import com.nikitakrapo.progressif.design.components.appbar.TopAppBar
import com.nikitakrapo.progressif.design.components.buttons.LargeButton
import com.nikitakrapo.progressif.design.components.screen.ScreenScaffold
import com.nikitakrapo.progressif.design.theme.PreviewTheme
import com.nikitakrapo.progressif.design.theme.ProgressifTheme
import com.nikitakrapo.progressif.design.theme.spacing
import com.nikitakrapo.progressif.design.utils.annotatedstring.buildAnnotatedString
import com.nikitakrapo.progressif.strings.Text
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
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .imePadding()
                    .scrollable(rememberScrollState(), orientation = Orientation.Vertical),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                item {
                    Text(
                        text = stringResource(Res.string.registration_title),
                        style = ProgressifTheme.typography.headlineMedium,
                        modifier = Modifier
                            .registrationItemWidth(),
                    )

                    Spacer(modifier = Modifier.height(ProgressifTheme.spacing.vertical.betweenComponents))
                }

                item {
                    EmailField(
                        email = state.email,
                        onEmailChange = component::onEmailChange,
                        error = state.error?.emailError?.resolve(),
                        modifier = Modifier
                            .registrationItemWidth(),
                    )

                    Spacer(modifier = Modifier.height(ProgressifTheme.spacing.vertical.betweenComponents))
                }

                item {
                    PasswordField(
                        password = state.password,
                        onPasswordChange = component::onPasswordChange,
                        imeAction = ImeAction.Done,
                        error = state.error?.passwordError?.resolve(),
                        modifier = Modifier
                            .registrationItemWidth(),
                    )

                    Spacer(modifier = Modifier.height(ProgressifTheme.spacing.vertical.componentToButton))
                }

                item {
                    LargeButton(
                        onClick = component::onSubmitClick,
                        text = stringResource(Res.string.registration_submit_button_text),
                        enabled = state.submitButtonEnabled,
                        modifier = Modifier
                            .registrationItemWidth(),
                    )
                    if (state.error?.showLogInPrompt == true) {
                        val loginLabel = buildAnnotatedString(
                            stringResource(Res.string.registration_already_registered_cta) to null,
                            " " to null,
                            stringResource(Res.string.registration_already_registered_sign_in_label) to SpanStyle(fontWeight = FontWeight.Bold)
                        )
                        Spacer(modifier = Modifier.height(ProgressifTheme.spacing.vertical.buttonToText))
                        Text(
                            text = loginLabel,
                            modifier = Modifier
                                .clickable(onClick = component::onLoginClick),
                        )
                    }
                }
            }
        },
    )
}

private fun Modifier.registrationItemWidth() = this
    .widthIn(max = AuthUiTokens.MaxFieldWidth)
    .fillMaxWidth()

@Preview
@Composable
private fun RegistrationScreenPreview() {
    PreviewTheme {
        RegistrationScreen(
            component = PreviewRegistrationComponent(),
        )
    }
}

@Preview
@Composable
private fun RegistrationScreenErrorPreview() {
    PreviewTheme {
        RegistrationScreen(
            component = PreviewRegistrationComponent(
                error = RegistrationErrorState(
                    emailError = Text.Raw("email already in use"),
                    passwordError = null,
                    generalError = null,
                    showLogInPrompt = true,
                )
            ),
        )
    }
}

private fun PreviewRegistrationComponent(error: RegistrationErrorState? = null) = object : RegistrationComponent {
    override val state: StateFlow<RegistrationState> = MutableStateFlow(
        RegistrationState(
            email = "cool@email.com",
            password = "password123",
            isLoading = false,
            error = error,
        )
    )

    override fun onEmailChange(value: String) {}

    override fun onPasswordChange(value: String) {}

    override fun onSubmitClick() {}

    override fun onLoginClick() {}

    override fun onBackClick() {}
}
