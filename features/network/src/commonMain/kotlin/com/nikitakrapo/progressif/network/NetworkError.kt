package com.nikitakrapo.progressif.network

sealed interface NetworkError<E> {

    /**
     * Request reached the server, and it returned a non-success status code.
     * @param code is the HTTP status code (e.g. 404, 500).
     * @param body is the parsed error response from your backend.
     */
    data class Server<E>(val code: Int, val body: E?) : NetworkError<E>

    /**
     * There is no confirmation that request has reached the server
     * OR the server returned malformed response
     */
    sealed interface Client<E> : NetworkError<E> {

        class Connectivity<E> : Client<E>

        class Timeout<E> : Client<E>

        class Parse<E> : Client<E>

        data class Unknown<E>(val throwable: Throwable) : Client<E>
    }
}