package com.nikitakrapo.progressif.firebase.auth.user

import com.nikitakrapo.progressif.firebase.auth.errors.FirebaseAuthException
import kotlinx.coroutines.tasks.await
import com.google.firebase.auth.FirebaseUser as AndroidFirebaseUser

actual class FirebaseUser(
    private val androidFirebaseUser: AndroidFirebaseUser,
) {
    actual val uid = androidFirebaseUser.uid

    @Throws(FirebaseAuthException::class)
    actual suspend fun delete() {
        try {
            androidFirebaseUser.delete().await()
        } catch (e: Exception) {
            throw FirebaseAuthException(e.message)
        }
    }
}