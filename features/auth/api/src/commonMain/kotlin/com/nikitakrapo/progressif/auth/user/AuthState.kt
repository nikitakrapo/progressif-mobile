package com.nikitakrapo.progressif.auth.user

sealed interface AuthState {
    data object SignedOut : AuthState
    data class SignedIn(val user: User) : AuthState
}
