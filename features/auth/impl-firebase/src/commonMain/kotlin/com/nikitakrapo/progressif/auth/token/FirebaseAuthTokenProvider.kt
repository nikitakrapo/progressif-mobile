package com.nikitakrapo.progressif.auth.token

import com.nikitakrapo.progressif.firebase.auth.FirebaseAuth
import com.nikitakrapo.progressif.network.AuthTokenProvider
import io.github.aakira.napier.Napier

class FirebaseAuthTokenProvider : AuthTokenProvider {

    override suspend fun getToken(forceRefresh: Boolean): String? {
        return try {
            FirebaseAuth.getIdToken(forceRefresh)
        } catch (e: Exception) {
            Napier.e(e) { "Error while fetching id token" }
            null
        }
    }
}
