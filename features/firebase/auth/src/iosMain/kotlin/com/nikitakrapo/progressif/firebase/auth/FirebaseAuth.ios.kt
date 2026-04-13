package com.nikitakrapo.progressif.firebase.auth

import com.nikitakrapo.progressif.firebase.auth.user.FirebaseUser
import kotlinx.coroutines.flow.StateFlow

actual object FirebaseAuth {

    actual val user: FirebaseUser? = TODO("Not yet implemented")

    actual val userFlow: StateFlow<FirebaseUser?> = TODO("Not yet implemented")

    actual suspend fun createUserWithEmailAndPassword(
        email: String,
        password: String
    ): FirebaseUser? {
        TODO("Not yet implemented")
    }

    actual suspend fun signInWithEmailAndPassword(email: String, password: String): FirebaseUser? {
        TODO("Not yet implemented")
    }

    actual suspend fun signOut() {
        TODO("Not yet implemented")
    }
}
