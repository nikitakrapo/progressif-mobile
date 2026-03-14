package com.nikitakrapo.progressif.network

sealed interface NetworkError {

    /**
     * Request reached the server, and it returned a non-success status code.
     * @param code is the HTTP status code (e.g. 404, 500).
     * @param body is the parsed error response from your backend.
     */
    data class ServerError<E>(val code: Int, val body: E?) : NetworkError

    data object Connectivity : NetworkError

    data object Timeout : NetworkError

    data object ParseError : NetworkError

    data class Unknown(val throwable: Throwable) : NetworkError
}