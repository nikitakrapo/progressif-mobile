package com.nikitakrapo.progressif.repositories.progressions.errors

import com.nikitakrapo.progressif.domain.models.error.FetchError
import com.nikitakrapo.progressif.network.NetworkError

internal fun <T> NetworkError<T>.toFetchError(): FetchError {
    return when (this) {
        is NetworkError.Server<*> -> FetchError.Network.Server
        is NetworkError.Client.Connectivity -> FetchError.Network.Connectivity
        is NetworkError.Client.Parse -> FetchError.Network.Server
        is NetworkError.Client.Timeout -> FetchError.Network.Connectivity
        is NetworkError.Client.Unknown -> FetchError.Network.Unknown
    }
}