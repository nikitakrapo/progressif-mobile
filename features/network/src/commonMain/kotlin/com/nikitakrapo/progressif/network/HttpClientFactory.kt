package com.nikitakrapo.progressif.network

import com.nikitakrapo.progressif.locale.UserLocaleProvider
import io.github.aakira.napier.Napier
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

expect fun createHttpClientEngine(): HttpClientEngine

class HttpClientFactory(
    private val networkConfig: NetworkConfig,
    private val authTokenProvider: AuthTokenProvider,
    private val userLocaleProvider: UserLocaleProvider,
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
                header(HttpHeaders.AcceptLanguage, userLocaleProvider.locale)
                header(HttpHeaders.ContentType, ContentType.Application.Json)
            }
            install(HttpTimeout) {
                requestTimeoutMillis = networkConfig.timeout.inWholeMilliseconds
            }
            install(Auth) {
                bearer {
                    loadTokens {
                        authTokenProvider.getToken(forceRefresh = false)?.let { BearerTokens(it, null) }
                    }
                    refreshTokens {
                        authTokenProvider.getToken(forceRefresh = true)?.let { BearerTokens(it, null) }
                    }
                    sendWithoutRequest { true }
                }
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
