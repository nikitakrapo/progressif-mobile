package com.nikitakrapo.progressif

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform