package com.nikitakrapo.progressif.network

import io.github.aakira.napier.Napier
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
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
            install(Logging) {
                level = LogLevel.INFO
                logger = object : Logger {
                    override fun log(message: String) {
                        Napier.v(tag = "HttpClient", message = message)
                    }
                }
            }
        }
    }
}
