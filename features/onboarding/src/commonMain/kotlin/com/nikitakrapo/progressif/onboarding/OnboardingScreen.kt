package com.nikitakrapo.progressif.onboarding

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nikitakrapo.progressf.strings.Res
import com.nikitakrapo.progressf.strings.onboarding_submit_button_text
import com.nikitakrapo.progressf.strings.onboarding_title
import com.nikitakrapo.progressif.design.components.buttons.LargeButton
import com.nikitakrapo.progressif.design.components.screen.ScreenScaffold
import com.nikitakrapo.progressif.design.theme.PreviewTheme
import com.nikitakrapo.progressif.design.theme.ProgressifTheme
import com.nikitakrapo.progressif.design.theme.spacing
import com.nikitakrapo.progressif.onboarding.ui.UsernameField
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.jetbrains.compose.resources.stringResource

@Composable
fun OnboardingScreen(
    component: OnboardingComponent,
) {
    val state by component.state.collectAsState()

    ScreenScaffold(
        content = { paddingValues ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .imePadding(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                item {
                    Text(
                        text = stringResource(Res.string.onboarding_title),
                        style = ProgressifTheme.typography.headlineMedium,
                        modifier = Modifier
                            .onboardingItemWidth(),
                    )

                    Spacer(modifier = Modifier.height(ProgressifTheme.spacing.vertical.betweenComponents))
                }

                item {
                    UsernameField(
                        username = state.username,
                        onUsernameChange = component::onUsernameChange,
                        modifier = Modifier
                            .onboardingItemWidth(),
                        error = state.usernameError?.resolve(),
                        imeAction = ImeAction.Done,
                    )

                    Spacer(modifier = Modifier.height(ProgressifTheme.spacing.vertical.componentToButton))
                }

                item {
                    LargeButton(
                        text = stringResource(Res.string.onboarding_submit_button_text),
                        onClick = component::onSubmitClick,
                        enabled = state.submitButtonEnabled,
                        modifier = Modifier
                            .onboardingItemWidth(),
                    )
                    state.generalError?.resolve()?.let {
                        Spacer(modifier = Modifier.height(ProgressifTheme.spacing.vertical.buttonToText))
                        Text(
                            text = it,
                            color = ProgressifTheme.colorScheme.error,
                        )
                    }
                }
            }
        },
    )
}

private fun Modifier.onboardingItemWidth() = this
    .widthIn(max = 360.dp)
    .fillMaxWidth()

@Preview
@Composable
private fun OnboardingScreenPreview() {
    PreviewTheme {
        OnboardingScreen(
            component = PreviewOnboardingComponent(),
        )
    }
}

private fun PreviewOnboardingComponent() = object : OnboardingComponent {
    override val state: StateFlow<OnboardingState> = MutableStateFlow(
        OnboardingState(
            username = "patsan",
            isLoading = false,
            usernameError = null,
            generalError = null,
        )
    )

    override fun onUsernameChange(value: String) {}

    override fun onSubmitClick() {}
}
