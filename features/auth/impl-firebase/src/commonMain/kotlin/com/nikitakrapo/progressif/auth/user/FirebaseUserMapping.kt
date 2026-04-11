package com.nikitakrapo.progressif.auth.user

import dev.gitlive.firebase.auth.FirebaseUser

internal fun FirebaseUser?.toUser(): User? {
    if (this == null) {
        return null
    }

    return User(
        id = uid,
    )
}
