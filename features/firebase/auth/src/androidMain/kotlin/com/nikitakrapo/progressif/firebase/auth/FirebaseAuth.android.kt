package com.nikitakrapo.progressif.firebase.auth

import com.nikitakrapo.progressif.firebase.auth.errors.FirebaseAuthException
import com.nikitakrapo.progressif.firebase.auth.errors.FirebaseAuthWeakPasswordException
import com.nikitakrapo.progressif.firebase.auth.user.FirebaseUser
import com.nikitakrapo.progressif.firebase.auth.user.toFirebaseUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.tasks.await
import com.google.firebase.auth.FirebaseAuth as AndroidFirebaseAuth
import com.google.firebase.auth.FirebaseAuthWeakPasswordException as AndroidFirebaseAuthWeakPasswordException

actual object FirebaseAuth {

    private val androidAuth by lazy { AndroidFirebaseAuth.getInstance() }

    actual val user get() = androidAuth.currentUser?.toFirebaseUser()

    actual val userFlow: StateFlow<FirebaseUser?>
        field = MutableStateFlow(user)

    init {
        androidAuth.addAuthStateListener {
            userFlow.value = it.currentUser?.toFirebaseUser()
        }
    }

    actual suspend fun createUserWithEmailAndPassword(email: String, password: String): FirebaseUser? {
        return try {
            androidAuth.createUserWithEmailAndPassword(email, password)
                .await()
                .user
                ?.toFirebaseUser()
        } catch (e: AndroidFirebaseAuthWeakPasswordException) {
            throw FirebaseAuthWeakPasswordException(e.reason)
        } catch (e: Exception) {
            throw FirebaseAuthException(e.message)
        }
    }

    actual suspend fun signInWithEmailAndPassword(email: String, password: String): FirebaseUser? {
        return try {
            androidAuth.signInWithEmailAndPassword(email, password)
                .await()
                .user
                ?.toFirebaseUser()
        } catch (e: Exception) {
            throw FirebaseAuthException(e.message)
        }
    }

    actual suspend fun signOut() {
        try {
            androidAuth.signOut()
        } catch (e: Exception) {
            throw FirebaseAuthException(e.message)
        }
    }
}
