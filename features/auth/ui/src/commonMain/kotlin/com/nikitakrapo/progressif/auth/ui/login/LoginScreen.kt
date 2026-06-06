package com.nikitakrapo.progressif.auth.ui.login

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
import androidx.compose.ui.text.input.ImeAction
import com.nikitakrapo.progressf.strings.Res
import com.nikitakrapo.progressf.strings.login_submit_button_text
import com.nikitakrapo.progressf.strings.login_title
import com.nikitakrapo.progressif.auth.ui.common.AuthUiTokens
import com.nikitakrapo.progressif.auth.ui.common.EmailField
import com.nikitakrapo.progressif.auth.ui.common.PasswordField
import com.nikitakrapo.progressif.design.components.appbar.NavigationButtonConfig
import com.nikitakrapo.progressif.design.components.appbar.TopAppBar
import com.nikitakrapo.progressif.design.components.buttons.LargeButton
import com.nikitakrapo.progressif.design.components.screen.ScreenScaffold
import com.nikitakrapo.progressif.design.theme.ProgressifTheme
import com.nikitakrapo.progressif.design.theme.spacing
import org.jetbrains.compose.resources.stringResource

@Composable
fun LoginScreen(
    component: LoginComponent,
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
                        text = stringResource(Res.string.login_title),
                        style = ProgressifTheme.typography.headlineMedium,
                        modifier = Modifier
                            .widthIn(max = AuthUiTokens.MaxFieldWidth)
                            .fillMaxWidth(),
                    )

                    Spacer(modifier = Modifier.height(ProgressifTheme.spacing.vertical.betweenComponents))
                }

                item {
                    EmailField(
                        email = state.email,
                        onEmailChange = component::onEmailChange,
                        modifier = Modifier
                            .widthIn(max = AuthUiTokens.MaxFieldWidth)
                            .fillMaxWidth(),
                    )

                    Spacer(modifier = Modifier.height(ProgressifTheme.spacing.vertical.betweenComponents))
                }

                item {
                    PasswordField(
                        password = state.password,
                        onPasswordChange = component::onPasswordChange,
                        imeAction = ImeAction.Done,
                        modifier = Modifier
                            .widthIn(max = AuthUiTokens.MaxFieldWidth)
                            .fillMaxWidth(),
                    )

                    Spacer(modifier = Modifier.height(ProgressifTheme.spacing.vertical.componentToButton))
                }

                item {
                    LargeButton(
                        onClick = component::onSubmitClick,
                        text = stringResource(Res.string.login_submit_button_text),
                        enabled = state.submitButtonEnabled,
                        modifier = Modifier
                            .widthIn(max = AuthUiTokens.MaxFieldWidth)
                            .fillMaxWidth(),
                    )
                }
            }
        },
    )
}
