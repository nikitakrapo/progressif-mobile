package com.nikitakrapo.progressif.network

import kotlin.time.Duration.Companion.seconds

internal val ProgressifNetworkConfig = NetworkConfig(
    baseUrl = "https://nikitakrapo.com",
    timeout = 15.seconds,
)