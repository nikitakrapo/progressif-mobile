package com.nikitakrapo.progressif.auth.user

sealed interface PatchUserError {

    data object UsernameTaken : PatchUserError

    data object Unknown : PatchUserError
}