package com.nikitakrapo.progressif.firebase.auth

import com.nikitakrapo.progressif.firebase.auth.errors.FirebaseAuthException
import com.nikitakrapo.progressif.firebase.auth.user.FirebaseUser
import kotlinx.coroutines.flow.StateFlow

expect object FirebaseAuth {

    val user: FirebaseUser?

    val userFlow: StateFlow<FirebaseUser?>

    @Throws(FirebaseAuthException::class)
    suspend fun createUserWithEmailAndPassword(email: String, password: String): FirebaseUser?

    @Throws(FirebaseAuthException::class)
    suspend fun signInWithEmailAndPassword(email: String, password: String): FirebaseUser?

    @Throws(FirebaseAuthException::class)
    suspend fun signOut()
}
