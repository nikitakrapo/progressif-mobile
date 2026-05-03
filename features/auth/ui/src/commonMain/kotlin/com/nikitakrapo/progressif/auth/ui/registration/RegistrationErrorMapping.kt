package com.nikitakrapo.progressif.auth.ui.registration

import androidx.compose.runtime.Composable
import com.nikitakrapo.progressf.strings.Res
import com.nikitakrapo.progressf.strings.common_error_unknown
import com.nikitakrapo.progressf.strings.registration_error_invalid_email
import com.nikitakrapo.progressf.strings.registration_error_weak_password
import com.nikitakrapo.progressif.auth.user.RegistrationError
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun RegistrationError.toMessage(): String = when (this) {
    RegistrationError.InvalidEmail -> stringResource(Res.string.registration_error_invalid_email)
    RegistrationError.WeakPassword -> stringResource(Res.string.registration_error_weak_password)
    RegistrationError.Unknown -> stringResource(Res.string.common_error_unknown)
}
