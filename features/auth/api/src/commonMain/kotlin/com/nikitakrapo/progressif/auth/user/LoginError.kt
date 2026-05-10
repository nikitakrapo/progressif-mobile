package com.nikitakrapo.progressif.auth.user

sealed interface LoginError {

    data object Unknown : LoginError
}
