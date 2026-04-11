package com.nikitakrapo.progressif.auth.user

sealed interface SignInError {

    data object Unknown : SignInError
}
