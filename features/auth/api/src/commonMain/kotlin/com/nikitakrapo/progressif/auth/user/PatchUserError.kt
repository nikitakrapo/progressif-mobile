package com.nikitakrapo.progressif.auth.user

sealed interface PatchUserError {

    data object UsernameTaken : PatchUserError

    data class UsernameInvalid(val message: String) : PatchUserError

    data object Unknown : PatchUserError
}