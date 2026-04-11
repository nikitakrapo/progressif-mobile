package com.nikitakrapo.progressif.auth.user

sealed interface SignUpError {

    data object WeakPassword : SignUpError

    data object Unknown : SignUpError
}
