package com.nikitakrapo.progressif.profile.ui

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.nikitakrapo.progressf.strings.Res
import com.nikitakrapo.progressf.strings.common_cancel
import com.nikitakrapo.progressf.strings.profile_logout_confirm_text
import com.nikitakrapo.progressf.strings.profile_logout_dialog_text
import com.nikitakrapo.progressf.strings.profile_logout_dialog_title
import com.nikitakrapo.progressif.design.components.screen.ScreenScaffold
import com.nikitakrapo.progressif.profile.ProfileComponent
import com.nikitakrapo.progressif.profile.ProfileStore
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    component: ProfileComponent,
) {
    val state by component.state.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }

    ScreenScaffold(
        snackbarHostState = snackbarHostState,
        content = {},
    )

    if (state.isLogoutConfirmationShown) {
        AlertDialog(
            onDismissRequest = {
                component.accept(ProfileStore.Intent.DismissLogoutConfirmation)
            },
            confirmButton = {
                Text(text = stringResource(Res.string.profile_logout_confirm_text))
            },
            dismissButton = {
                Text(text = stringResource(Res.string.common_cancel))
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
