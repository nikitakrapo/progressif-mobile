package com.nikitakrapo.progressif.firebase.auth.user

import com.nikitakrapo.progressif.firebase.auth.errors.FirebaseAuthException

expect class FirebaseUser {
    val uid: String

    @Throws(FirebaseAuthException::class)
    suspend fun delete()
}
