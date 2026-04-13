package com.nikitakrapo.progressif.auth.errors

import com.nikitakrapo.progressif.firebase.auth.errors.FirebaseAuthException

open class FirebaseAuthException(message: String?) : Exception(message)
class FirebaseAuthWeakPasswordException(reason: String?) : FirebaseAuthException(reason)