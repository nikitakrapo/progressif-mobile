package com.nikitakrapo.progressif.network

fun interface AuthTokenProvider {
    suspend fun getToken(forceRefresh: Boolean): String?
}
