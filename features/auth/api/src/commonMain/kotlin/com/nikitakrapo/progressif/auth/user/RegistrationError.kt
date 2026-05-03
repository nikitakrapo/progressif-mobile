package com.nikitakrapo.progressif.auth.user

sealed interface RegistrationError {

    data object InvalidEmail : RegistrationError

    data object WeakPassword : RegistrationError

    data object Unknown : RegistrationError
}
