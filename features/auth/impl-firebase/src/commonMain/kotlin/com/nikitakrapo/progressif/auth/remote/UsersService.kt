package com.nikitakrapo.progressif.auth.remote

import com.nikitakrapo.progressif.network.NetworkError
import com.nikitakrapo.progressif.network.executeRequest
import com.nikitakrapo.progressif.result.Result
import io.ktor.client.HttpClient
import io.ktor.client.request.get
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

    suspend fun updateUser(
        updateUserDto: UpdateUserDto,
    ): Result<UserDto, NetworkError<Unit>> {
        return executeRequest(
            request = {
                httpClient.patch("/users"){
                    setBody(updateUserDto)
                }
            },
        )
    }
}
