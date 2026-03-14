package com.nikitakrapo.progressif.network

import kotlin.time.Duration.Companion.seconds

internal val ProgressifNetworkConfig = NetworkConfig(
    baseUrl = "https://google.com",
    timeout = 15.seconds,
)