package com.nikitakrapo.progressif.network

import kotlin.time.Duration.Companion.seconds

internal val ProgressifNetworkConfig = NetworkConfig(
    baseUrl = "http://10.0.0.2:8080",
    timeout = 15.seconds,
)