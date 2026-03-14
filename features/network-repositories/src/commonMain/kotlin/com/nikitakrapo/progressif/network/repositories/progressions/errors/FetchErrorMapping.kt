package com.nikitakrapo.progressif.network.repositories.progressions.errors

import com.nikitakrapo.progressif.domain.models.error.FetchError
import com.nikitakrapo.progressif.network.NetworkError

internal fun NetworkError.toFetchError(): FetchError {
    return when (this) {
        NetworkError.Connectivity -> FetchError.Network.Connectivity
        NetworkError.ParseError -> FetchError.Network.Server
        is NetworkError.ServerError<*> -> FetchError.Network.Server
        NetworkError.Timeout -> FetchError.Network.Connectivity
        is NetworkError.Unknown -> FetchError.Network.Unknown
    }
}