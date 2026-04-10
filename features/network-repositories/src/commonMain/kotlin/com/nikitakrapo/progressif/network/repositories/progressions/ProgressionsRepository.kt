package com.nikitakrapo.progressif.network.repositories.progressions

import com.nikitakrapo.progressif.domain.models.Progression
import com.nikitakrapo.progressif.domain.models.error.FetchError
import com.nikitakrapo.progressif.network.repositories.progressions.errors.toFetchError
import com.nikitakrapo.progressif.result.Result
import io.ktor.client.HttpClient
import kotlinx.coroutines.delay

interface ProgressionsRepository {
    suspend fun getProgressions(): Result<List<Progression>, FetchError>
}

class ProgressionsRepositoryImpl(
    private val httpClient: HttpClient,
) : ProgressionsRepository {

    private val progressionsService by lazy { ProgressionsService(httpClient) }

    override suspend fun getProgressions(): Result<List<Progression>, FetchError> {
        val networkResult = progressionsService.getProgressions()
        return networkResult
            .mapSuccess { it.toProgressionsList() }
            .mapFailure { it.toFetchError() }
    }
}

class FakeProgressionsRepository : ProgressionsRepository {
    override suspend fun getProgressions(): Result<List<Progression>, FetchError> {
        delay(1000)
        return Result.Success(
            listOf(
            )
        )
    }
}
