package com.nikitakrapo.progressif.auth.user

import com.nikitakrapo.progressif.result.Result
import kotlinx.coroutines.flow.StateFlow

interface UserRepository {

    val state: StateFlow<AuthState>

    suspend fun register(email: String, password: String): Result<Unit, RegistrationError>

    suspend fun login(email: String, password: String): Result<Unit, LoginError>

    suspend fun logout(): Result<Unit, LogoutError>
}
