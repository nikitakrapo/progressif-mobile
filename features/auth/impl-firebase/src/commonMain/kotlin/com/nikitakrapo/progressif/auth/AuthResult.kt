package com.nikitakrapo.progressif.auth

import com.nikitakrapo.progressif.firebase.auth.user.FirebaseUser

data class AuthResult(
    val user: FirebaseUser?,
)
