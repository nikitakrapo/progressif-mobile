package com.nikitakrapo.progressif.auth.user

import com.nikitakrapo.progressif.auth.cache.UserCache
import com.nikitakrapo.progressif.auth.remote.UsersService
import com.nikitakrapo.progressif.auth.remote.toUser
import com.nikitakrapo.progressif.firebase.auth.FirebaseAuth
import com.nikitakrapo.progressif.firebase.auth.errors.FirebaseAuthException
import com.nikitakrapo.progressif.firebase.auth.errors.FirebaseAuthUserCollisionException
import com.nikitakrapo.progressif.firebase.auth.errors.FirebaseAuthWeakPasswordException
import com.nikitakrapo.progressif.result.Result
import io.github.aakira.napier.Napier
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

internal class UserRepositoryImpl(
    private val usersService: UsersService,
    private val userCache: UserCache,
    private val firebaseAuth: FirebaseAuth,
) : UserRepository {

    private val scope = CoroutineScope(Dispatchers.Default)

    init {
        firebaseAuth.userFlow
            .onEach { firebaseUser ->
                if (firebaseUser == null) {
                    stateFlow.value = AuthState.SignedOut
                }
            }
            .launchIn(scope)
    }

    private val stateFlow = MutableStateFlow(getInitialAuthState())
    override val state: StateFlow<AuthState> = stateFlow.asStateFlow()

    override suspend fun register(
        email: String,
        password: String
    ): Result<Unit, RegistrationError> {
        try {
            firebaseAuth.createUserWithEmailAndPassword(email, password)
        } catch (e: FirebaseAuthUserCollisionException) {
            Napier.e(e) { "Collision error while registering" }
            val error = RegistrationError(emailError = RegistrationError.EmailError.AlreadyInUse)
            return Result.Failure(error)
        } catch (e: FirebaseAuthWeakPasswordException) {
            Napier.e(e) { "Weak password error error while registering" }
            val error = RegistrationError(passwordError = RegistrationError.PasswordError.Weak)
            return Result.Failure(error)
        } catch (e: FirebaseAuthException) {
            Napier.e(e) { "Unknown error while registering" }
            return Result.Failure(RegistrationError())
        } ?: run {
            return Result.Failure(RegistrationError())
        }

        val backendUserResult = usersService.getMe()

        return when (backendUserResult) {
            is Result.Success -> {
                stateFlow.value = AuthState.SignedIn(backendUserResult.data.toUser())
                Result.Success(Unit)
            }
            is Result.Failure -> Result.Failure(RegistrationError())
        }
    }

    override suspend fun login(
        email: String,
        password: String
    ): Result<Unit, LoginError> {
        try {
            firebaseAuth.loginWithEmailAndPassword(email, password)
        } catch (e: FirebaseAuthException) {
            Napier.e(e) { "Error while logging in" }
            return Result.Failure(LoginError.Unknown)
        }

        val backendUserResult = usersService.getMe()

        return when (backendUserResult) {
            is Result.Success -> {
                stateFlow.value = AuthState.SignedIn(backendUserResult.data.toUser())
                Result.Success(Unit)
            }
            is Result.Failure -> Result.Failure(LoginError.Unknown)
        }
    }

    override suspend fun logout(): Result<Unit, LogoutError> {
        try {
            userCache.clear()
            firebaseAuth.signOut()
        } catch (e: FirebaseAuthException) {
            Napier.e(e) { "Error while logging out" }
            return Result.Failure(LogoutError.Unknown)
        } finally {
            stateFlow.value = AuthState.SignedOut
        }

        return Result.Success(Unit)
    }

    private fun getInitialAuthState(): AuthState {
        val user = userCache.read() ?: return AuthState.SignedOut
        return AuthState.SignedIn(user)
    }
}