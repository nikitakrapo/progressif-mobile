package com.nikitakrapo.progressif.auth.remote

import com.nikitakrapo.progressif.network.NetworkError
import com.nikitakrapo.progressif.network.executeRequest
import com.nikitakrapo.progressif.result.Result
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.patch
import io.ktor.client.request.setBody
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

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
    ): Result<UserDto, NetworkError<ErrorResponse>> {
        return executeRequest(
            request = {
                httpClient.patch("/users/$userId"){
                    setBody(patchUserDto)
                }
            },
        )
    }

    @Serializable
    data class ErrorResponse(
        @SerialName("code") val code: String,
        @SerialName("message") val message: String,
    )
}
