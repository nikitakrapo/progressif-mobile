package com.nikitakrapo.progressif.auth.user

sealed interface LogoutError {

    data object Unknown : LogoutError
}