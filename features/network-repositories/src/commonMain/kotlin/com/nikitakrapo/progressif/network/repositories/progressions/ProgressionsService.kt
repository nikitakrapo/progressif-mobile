package com.nikitakrapo.progressif.network.repositories.progressions

import com.nikitakrapo.progressif.network.NetworkError
import com.nikitakrapo.progressif.network.safeRequest
import com.nikitakrapo.progressif.result.Result
import io.ktor.client.HttpClient
import io.ktor.http.HttpMethod
import io.ktor.http.path

internal class ProgressionsService(
    private val httpClient: HttpClient,
) {

    suspend fun getProgressions(): Result<ProgressionsDto, NetworkError> {
        return httpClient.safeRequest<ProgressionsDto, NetworkError> {
            method = HttpMethod.Get
            url.path("progressions")
        }
    }
}