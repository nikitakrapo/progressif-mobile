package com.nikitakrapo.progressif.profile.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.nikitakrapo.progressf.strings.Res
import com.nikitakrapo.progressf.strings.common_cancel
import com.nikitakrapo.progressf.strings.profile_logout_confirm_text
import com.nikitakrapo.progressf.strings.profile_logout_dialog_text
import com.nikitakrapo.progressf.strings.profile_logout_dialog_title
import com.nikitakrapo.progressif.design.components.bottombar.BottomBarPadding
import com.nikitakrapo.progressif.design.components.screen.ScreenScaffold
import com.nikitakrapo.progressif.profile.ProfileComponent
import com.nikitakrapo.progressif.profile.ProfileSection
import com.nikitakrapo.progressif.profile.ProfileStore
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class, ExperimentalResourceApi::class)
@Composable
fun ProfileScreen(
    component: ProfileComponent,
) {
    val state by component.state.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(component) {
        component.events.collect { event ->
            when (event) {
                is ProfileStore.Label.ShowSnackbar -> {
                    val message = event.message.resolveAsync()
                    snackbarHostState.showSnackbar(message)
                }
            }
        }
    }

    ScreenScaffold(
        snackbarHostState = snackbarHostState,
        content = { paddingValues ->
            LazyColumn(contentPadding = paddingValues) {
                items(state.sections) { section ->
                    when (section) {
                        is ProfileSection.Header -> ProfileHeader(displayName = section.displayName)
                        is ProfileSection.Button -> {
                            TextButton(
                                onClick = { component.accept(section.intent) },
                                content = {
                                    Text(text = section.label.resolve())
                                },
                            )
                        }
                    }
                }
            }
        },
        modifier = Modifier
            .padding(BottomBarPadding()),
    )

    if (state.isLogoutConfirmationShown) {
        AlertDialog(
            onDismissRequest = {
                component.accept(ProfileStore.Intent.DismissLogoutConfirmation)
            },
            confirmButton = {
                TextButton(onClick = { component.accept(ProfileStore.Intent.AcceptLogoutConfirmation) }) {
                    Text(text = stringResource(Res.string.profile_logout_confirm_text))
                }
            },
            dismissButton = {
                TextButton(onClick = { component.accept(ProfileStore.Intent.DismissLogoutConfirmation) }) {
                    Text(text = stringResource(Res.string.common_cancel))
                }
            },
            title = {
                Text(text = stringResource(Res.string.profile_logout_dialog_title))
            },
            text = {
                Text(text = stringResource(Res.string.profile_logout_dialog_text))
            }
        )
    }
}
