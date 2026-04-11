package com.nikitakrapo.progressif.auth.user

import com.nikitakrapo.progressif.result.Result
import kotlinx.coroutines.flow.StateFlow

interface UserRepository {

    val user: StateFlow<User?>

    suspend fun signUp(email: String, password: String): Result<Unit, SignUpError>

    suspend fun signIn(email: String, password: String): Result<Unit, SignInError>

    suspend fun logout(): Result<Unit, LogoutError>
}