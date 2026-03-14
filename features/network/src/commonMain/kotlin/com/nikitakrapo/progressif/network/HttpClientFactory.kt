package com.nikitakrapo.progressif.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

expect fun createHttpClientEngine(): HttpClientEngine

class HttpClientFactory(
    private val networkConfig: NetworkConfig,
) {

    private val jsonInstance by lazy {
        Json {
            ignoreUnknownKeys = true
            prettyPrint = true
        }
    }

    fun createDefaultClient(): HttpClient {
        return HttpClient(createHttpClientEngine()) {
            install(ContentNegotiation) {
                json(jsonInstance)
            }
            install(DefaultRequest) {
                url(networkConfig.baseUrl)
            }
            install(HttpTimeout) {
                requestTimeoutMillis = networkConfig.timeout.inWholeMilliseconds
            }
        }
    }
}
