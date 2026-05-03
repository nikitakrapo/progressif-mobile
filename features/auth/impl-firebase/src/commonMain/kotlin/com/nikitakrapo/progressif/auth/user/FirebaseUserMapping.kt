package com.nikitakrapo.progressif.auth.user

import com.nikitakrapo.progressif.firebase.auth.user.FirebaseUser

internal fun FirebaseUser?.toUser(): User? {
    if (this == null) return null
    return User(
        id = uid,
        displayName = displayName,
    )
}
