package com.nikitakrapo.progressif.network.repositories.progressions

import com.nikitakrapo.progressif.network.NetworkError
import com.nikitakrapo.progressif.network.safeRequest
import com.nikitakrapo.progressif.result.Result
import io.ktor.client.HttpClient
import io.ktor.client.request.get

internal class ProgressionsService(
    private val httpClient: HttpClient,
) {

    suspend fun getProgressions(): Result<ProgressionsDto, NetworkError> =
        safeRequest<ProgressionsDto, NetworkError>(
            request = {
                httpClient.get("progressions")
            },
        )
}