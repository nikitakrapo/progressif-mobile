package com.nikitakrapo.progressif.auth.user

import com.nikitakrapo.progressif.result.Result
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.FirebaseAuthWeakPasswordException
import dev.gitlive.firebase.auth.auth
import io.github.aakira.napier.Napier
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class UserRepositoryImpl : UserRepository {

    private val scope = CoroutineScope(Dispatchers.Default)

    private val auth by lazy { Firebase.auth }

    override val user: StateFlow<User?> = auth.authStateChanged
        .map { it.toUser() }
        .stateIn(
            scope = scope,
            started = SharingStarted.Eagerly,
            initialValue = auth.currentUser.toUser(),
        )

    override suspend fun signUp(email: String, password: String): Result<Unit, SignUpError> {
        return try {
            val result = auth.createUserWithEmailAndPassword(email, password)
            if (result.user != null) {
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
            val result = auth.signInWithEmailAndPassword(email, password)
            if (result.user != null) {
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