package com.nikitakrapo.progressif.network

import kotlin.time.Duration

data class NetworkConfig(
    val baseUrl: String,
    val timeout: Duration,
)
