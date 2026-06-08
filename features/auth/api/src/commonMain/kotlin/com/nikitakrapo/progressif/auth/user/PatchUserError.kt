package com.nikitakrapo.progressif.auth.user

sealed interface PatchUserError {

    data object Unknown : PatchUserError
}