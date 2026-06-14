package com.nikitakrapo.progressif.repositories.tricks

import com.nikitakrapo.progressif.domain.models.Trick
import com.nikitakrapo.progressif.domain.models.TrickDetails
import com.nikitakrapo.progressif.domain.models.error.FetchError
import com.nikitakrapo.progressif.repositories.progressions.errors.toFetchError
import com.nikitakrapo.progressif.result.Result
import io.ktor.client.HttpClient

interface TricksRepository {
    suspend fun getTricks(): Result<List<Trick>, FetchError>

    suspend fun getTrickDetails(id: String): Result<TrickDetails, FetchError>
}

class TricksRepositoryImpl(
    private val httpClient: HttpClient,
) : TricksRepository {

    private val tricksService by lazy { TricksService(httpClient) }

    override suspend fun getTricks(): Result<List<Trick>, FetchError> {
        return tricksService.getTricks()
            .mapSuccess { it.toTricks() }
            .mapFailure { it.toFetchError() }
    }

    override suspend fun getTrickDetails(id: String): Result<TrickDetails, FetchError> {
        return tricksService.getTrickDetails(id)
            .mapSuccess { it.toTrickDetails() }
            .mapFailure { it.toFetchError() }
    }
}
