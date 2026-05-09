package com.nikitakrapo.progressif.auth.remote

import com.nikitakrapo.progressif.network.NetworkError
import com.nikitakrapo.progressif.network.safeRequest
import com.nikitakrapo.progressif.result.Result
import io.ktor.client.HttpClient
import io.ktor.client.request.get

internal class UsersService(
    private val httpClient: HttpClient,
) {

    suspend fun getMe(): Result<UserDto, NetworkError> =
        safeRequest<UserDto, NetworkError>(
            request = {
                httpClient.get("users/me")
            },
        )
}
