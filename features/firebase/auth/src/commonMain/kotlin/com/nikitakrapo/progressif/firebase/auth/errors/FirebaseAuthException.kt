package com.nikitakrapo.progressif.firebase.auth.errors

open class FirebaseAuthException(message: String?) : Exception(message)
class FirebaseAuthWeakPasswordException(reason: String?) : FirebaseAuthException(reason)