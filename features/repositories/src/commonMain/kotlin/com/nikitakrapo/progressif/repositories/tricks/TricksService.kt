package com.nikitakrapo.progressif.repositories.tricks

import com.nikitakrapo.progressif.network.NetworkError
import com.nikitakrapo.progressif.network.executeRequest
import com.nikitakrapo.progressif.result.Result
import io.ktor.client.HttpClient
import io.ktor.client.request.get

internal class TricksService(
    private val httpClient: HttpClient,
) {

    suspend fun getTricks(): Result<List<TrickDto>, NetworkError<Unit>> {
        return executeRequest(
            request = {
                httpClient.get("tricks")
            },
        )
    }

    suspend fun getTrickDetails(id: String): Result<TrickDetailsDto, NetworkError<Unit>> {
        return executeRequest(
            request = {
                httpClient.get("tricks/$id")
            },
        )
    }
}
