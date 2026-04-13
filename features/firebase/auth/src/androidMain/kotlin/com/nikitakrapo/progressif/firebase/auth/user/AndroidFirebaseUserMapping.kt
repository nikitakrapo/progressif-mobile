package com.nikitakrapo.progressif.firebase.auth.user

import com.google.firebase.auth.FirebaseUser as AndroidFirebaseUser

internal fun AndroidFirebaseUser.toFirebaseUser() = FirebaseUser(
    uid = uid,
)
