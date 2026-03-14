package com.nikitakrapo.progressif.di

import com.nikitakrapo.progressif.network.HttpClientFactory
import com.nikitakrapo.progressif.network.ProgressifNetworkConfig
import io.ktor.client.HttpClient
import org.koin.dsl.module

internal val NetworkModule = module {
    single<HttpClientFactory> { HttpClientFactory(networkConfig = ProgressifNetworkConfig) }
    single<HttpClient> { get<HttpClientFactory>().createDefaultClient() }
}