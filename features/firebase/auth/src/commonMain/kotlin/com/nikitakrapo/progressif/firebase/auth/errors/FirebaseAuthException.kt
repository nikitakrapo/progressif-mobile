package com.nikitakrapo.progressif.firebase.auth.errors

open class FirebaseAuthException(message: String?) : Exception(message)
class FirebaseAuthInvalidCredentialsException(message: String?) : FirebaseAuthException(message)
class FirebaseAuthWeakPasswordException(reason: String?) : FirebaseAuthException(reason)
class FirebaseAuthUserCollisionException(reason: String?) : FirebaseAuthException(reason)