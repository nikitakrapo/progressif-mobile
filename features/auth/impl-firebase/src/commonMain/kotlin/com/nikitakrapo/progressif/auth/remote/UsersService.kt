package com.nikitakrapo.progressif.auth.remote

import com.nikitakrapo.progressif.network.NetworkError
import com.nikitakrapo.progressif.network.executeRequest
import com.nikitakrapo.progressif.result.Result
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.patch
import io.ktor.client.request.setBody

internal class UsersService(
    private val httpClient: HttpClient,
) {

    suspend fun getMe(): Result<UserDto, NetworkError<Unit>> {
        return executeRequest(
            request = {
                httpClient.get("/users/me")
            },
        )
    }

    suspend fun patchUser(
        userId: String,
        patchUserDto: PatchUserDto,
    ): Result<UserDto, NetworkError<Unit>> {
        return executeRequest(
            request = {
                httpClient.patch("/users/{id}"){
                    parameter("id", userId)
                    setBody(patchUserDto)
                }
            },
        )
    }
}
