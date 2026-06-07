package com.nikitakrapo.progressif.firebase.auth.user

import com.nikitakrapo.progressif.firebase.auth.errors.FirebaseAuthException
import kotlinx.coroutines.CancellationException

actual class FirebaseUser {
    actual val uid: String
        get() = TODO("Not yet implemented")

    @Throws(exceptionClasses = [FirebaseAuthException::class, CancellationException::class])
    actual suspend fun delete() {
        TODO("Not yet implemented")
    }
}