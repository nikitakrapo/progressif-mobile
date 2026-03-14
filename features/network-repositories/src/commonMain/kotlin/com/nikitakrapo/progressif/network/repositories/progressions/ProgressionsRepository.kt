package com.nikitakrapo.progressif.network.repositories.progressions

import com.nikitakrapo.progressif.domain.models.Progression
import com.nikitakrapo.progressif.domain.models.error.FetchError
import com.nikitakrapo.progressif.network.repositories.progressions.errors.toFetchError
import com.nikitakrapo.progressif.result.Result
import io.ktor.client.HttpClient

class ProgressionsRepository(
    private val httpClient: HttpClient,
) {

    private val progressionsService by lazy { ProgressionsService(httpClient) }

    suspend fun getProgressions(): Result<List<Progression>, FetchError> {
        val networkResult = progressionsService.getProgressions()
        return networkResult
            .mapSuccess { it.toProgressionsList() }
            .mapFailure { it.toFetchError() }
    }
}