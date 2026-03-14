package com.nikitakrapo.progressif.domain.models.error

sealed interface FetchError {

    sealed interface Network : FetchError {

        data object Connectivity : Network

        data object Server : Network

        data object Unknown : Network
    }
}