package com.nikitakrapo.progressif.auth.user

import com.nikitakrapo.progressif.firebase.auth.FirebaseAuth
import com.nikitakrapo.progressif.firebase.auth.errors.FirebaseAuthWeakPasswordException
import com.nikitakrapo.progressif.result.Result
import io.github.aakira.napier.Napier
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class UserRepositoryImpl : UserRepository {

    private val scope = CoroutineScope(Dispatchers.Default)

    private val auth by lazy { FirebaseAuth }

    override val user: StateFlow<User?> = auth.userFlow
        .map { it.toUser() }
        .stateIn(
            scope = scope,
            started = SharingStarted.Eagerly,
            initialValue = auth.user.toUser(),
        )

    override suspend fun signUp(email: String, password: String): Result<Unit, SignUpError> {
        return try {
            val user = auth.createUserWithEmailAndPassword(email, password)
            if (user != null) {
                Result.Success(Unit)
            } else {
                Result.Failure(SignUpError.Unknown)
            }
        } catch (e: FirebaseAuthWeakPasswordException) {
            Result.Failure(SignUpError.WeakPassword)
        } catch (e: Exception) {
            Napier.e(e) { "Error while signing up" }
            Result.Failure(SignUpError.Unknown)
        }
    }

    override suspend fun signIn(email: String, password: String): Result<Unit, SignInError> {
        return try {
            val user = auth.signInWithEmailAndPassword(email, password)
            if (user != null) {
                Result.Success(Unit)
            } else {
                Result.Failure(SignInError.Unknown)
            }
        } catch (e: Exception) {
            Napier.e(e) { "Error while signing in" }
            Result.Failure(SignInError.Unknown)
        }
    }

    override suspend fun logout(): Result<Unit, LogoutError> {
        return try {
            auth.signOut()
            Result.Success(Unit)
        } catch (e: Exception) {
            Napier.e(e) { "Error while signing out" }
            Result.Failure(LogoutError.Unknown)
        }
    }
}