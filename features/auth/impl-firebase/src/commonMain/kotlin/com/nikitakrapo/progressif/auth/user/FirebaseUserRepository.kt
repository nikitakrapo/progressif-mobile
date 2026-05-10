package com.nikitakrapo.progressif.auth.user

import com.nikitakrapo.progressif.auth.cache.UserCache
import com.nikitakrapo.progressif.auth.remote.UsersService
import com.nikitakrapo.progressif.auth.remote.toUser
import com.nikitakrapo.progressif.firebase.auth.FirebaseAuth
import com.nikitakrapo.progressif.firebase.auth.errors.FirebaseAuthWeakPasswordException
import com.nikitakrapo.progressif.result.Result
import io.github.aakira.napier.Napier
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.selects.select

private val EMAIL_REGEX = Regex("""(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|"(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21\x23-\x5b\x5d-\x7f]|\\[\x01-\x09\x0b\x0c\x0e-\x7f])*")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21-\x5a\x53-\x7f]|\\[\x01-\x09\x0b\x0c\x0e-\x7f])+)\])""")

@OptIn(ExperimentalCoroutinesApi::class)
class FirebaseUserRepository(
    private val usersService: UsersService,
    private val userCache: UserCache,
) : UserRepository {

    private val scope = CoroutineScope(Dispatchers.Default)

    private val auth by lazy { FirebaseAuth }

    private val authErrors = MutableSharedFlow<Unit>(
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST,
    )

    override val state: StateFlow<AuthState> = auth.userFlow
        .flatMapLatest { firebaseUser ->
            if (firebaseUser == null) {
                flowOf(AuthState.SignedOut)
            } else {
                flow<AuthState> {
                    val cached = userCache.read(firebaseUser.uid)
                    if (cached != null) {
                        emit(AuthState.SignedIn(cached))
                    }
                    when (val result = usersService.getMe()) {
                        is Result.Success -> {
                            val user = result.data.toUser()
                            userCache.write(firebaseUser.uid, user)
                            emit(AuthState.SignedIn(user))
                        }
                        is Result.Failure -> {
                            Napier.e { "Error fetching /me: ${result.error}" }
                            if (cached == null) {
                                authErrors.tryEmit(Unit)
                                try {
                                    auth.signOut()
                                } catch (e: Exception) {
                                    Napier.e(e) { "Failed to sign out after /me failure" }
                                }
                            }
                        }
                    }
                }
            }
        }
        .stateIn(
            scope = scope,
            started = SharingStarted.Eagerly,
            initialValue = computeInitialState(),
        )

    override suspend fun register(email: String, password: String): Result<Unit, RegistrationError> {
        if (!EMAIL_REGEX.matches(email)) {
            return Result.Failure(RegistrationError.InvalidEmail)
        }

        return awaitAuthOutcome(
            firebaseAction = {
                try {
                    val user = auth.createUserWithEmailAndPassword(email, password)
                    if (user != null) Result.Success(Unit) else Result.Failure(RegistrationError.Unknown)
                } catch (e: FirebaseAuthWeakPasswordException) {
                    Result.Failure(RegistrationError.WeakPassword)
                } catch (e: Exception) {
                    Napier.e(e) { "Error while registering" }
                    Result.Failure(RegistrationError.Unknown)
                }
            },
            failureFallback = RegistrationError.Unknown,
        )
    }

    override suspend fun login(email: String, password: String): Result<Unit, LoginError> {
        return awaitAuthOutcome(
            firebaseAction = {
                try {
                    val user = auth.loginWithEmailAndPassword(email, password)
                    if (user != null) Result.Success(Unit) else Result.Failure(LoginError.Unknown)
                } catch (e: Exception) {
                    Napier.e(e) { "Error while logging in" }
                    Result.Failure(LoginError.Unknown)
                }
            },
            failureFallback = LoginError.Unknown,
        )
    }

    override suspend fun logout(): Result<Unit, LogoutError> {
        return try {
            auth.signOut()
            userCache.clear()
            Result.Success(Unit)
        } catch (e: Exception) {
            Napier.e(e) { "Error while signing out" }
            Result.Failure(LogoutError.Unknown)
        }
    }

    private fun computeInitialState(): AuthState {
        val firebaseUser = auth.user ?: return AuthState.SignedOut
        return userCache.read(firebaseUser.uid)
            ?.let { AuthState.SignedIn(it) }
            ?: AuthState.SignedOut
    }

    private suspend fun <E> awaitAuthOutcome(
        firebaseAction: suspend () -> Result<Unit, E>,
        failureFallback: E,
    ): Result<Unit, E> = coroutineScope {
        val signedInDeferred = async(start = CoroutineStart.UNDISPATCHED) {
            state.first { it is AuthState.SignedIn }
        }
        val failedDeferred = async(start = CoroutineStart.UNDISPATCHED) {
            authErrors.first()
        }

        val firebaseResult = firebaseAction()
        if (firebaseResult is Result.Failure) {
            signedInDeferred.cancel()
            failedDeferred.cancel()
            return@coroutineScope firebaseResult
        }

        select<Result<Unit, E>> {
            signedInDeferred.onAwait {
                failedDeferred.cancel()
                Result.Success(Unit)
            }
            failedDeferred.onAwait {
                signedInDeferred.cancel()
                Result.Failure(failureFallback)
            }
        }
    }
}
