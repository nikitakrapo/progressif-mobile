package com.nikitakrapo.progressif.auth.user

import com.nikitakrapo.progressif.result.Result
import kotlinx.coroutines.flow.StateFlow

interface UserRepository {

    val state: StateFlow<AuthState>

    suspend fun register(email: String, password: String): Result<Unit, RegistrationError>

    suspend fun signIn(email: String, password: String): Result<Unit, SignInError>

    suspend fun logout(): Result<Unit, LogoutError>
}
