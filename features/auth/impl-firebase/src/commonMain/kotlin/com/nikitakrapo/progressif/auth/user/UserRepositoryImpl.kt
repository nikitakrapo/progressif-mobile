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

private val EMAIL_REGEX = Regex("""(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|"(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21\x23-\x5b\x5d-\x7f]|\\[\x01-\x09\x0b\x0c\x0e-\x7f])*")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21-\x5a\x53-\x7f]|\\[\x01-\x09\x0b\x0c\x0e-\x7f])+)\])""")

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

    override suspend fun register(email: String, password: String): Result<Unit, RegistrationError> {
        if (!EMAIL_REGEX.matches(email)) {
            return Result.Failure(RegistrationError.InvalidEmail)
        }

        return try {
            val user = auth.createUserWithEmailAndPassword(email, password)
            if (user != null) {
                Result.Success(Unit)
            } else {
                Result.Failure(RegistrationError.Unknown)
            }
        } catch (e: FirebaseAuthWeakPasswordException) {
            Result.Failure(RegistrationError.WeakPassword)
        } catch (e: Exception) {
            Napier.e(e) { "Error while registering" }
            Result.Failure(RegistrationError.Unknown)
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
