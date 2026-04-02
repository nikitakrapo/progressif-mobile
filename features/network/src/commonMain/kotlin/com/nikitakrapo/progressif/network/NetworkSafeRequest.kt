package com.nikitakrapo.progressif.network

import com.nikitakrapo.progressif.result.Result
import io.ktor.client.call.NoTransformationFoundException
import io.ktor.client.call.body
import io.ktor.client.plugins.HttpRequestTimeoutException
import io.ktor.client.statement.HttpResponse
import io.ktor.http.isSuccess
import io.ktor.util.network.UnresolvedAddressException

suspend inline fun <reified T, reified E> safeRequest(
    request: () -> HttpResponse,
): Result<T, NetworkError> {
    return try {
        val response = request()

        if (response.status.isSuccess()) {
            val successBody = response.body<T>()
            Result.Success(successBody)
        } else {
            val errorBody = response.body<E>()
            Result.Failure(NetworkError.ServerError(response.status.value, errorBody))
        }
    } catch (_: UnresolvedAddressException) {
        val error = NetworkError.Connectivity
        Result.Failure(error)
    } catch (_: HttpRequestTimeoutException) {
        val error = NetworkError.Timeout
        Result.Failure(error)
    } catch (_: NoTransformationFoundException) {
        val error = NetworkError.ParseError
        Result.Failure(error)
    } catch (e: Exception) {
        val error = NetworkError.Unknown(e)
        Result.Failure(error)
    }
}