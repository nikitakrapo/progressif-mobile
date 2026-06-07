package com.nikitakrapo.progressif.auth.user

data class User(
    val id: String,
    val email: String?,
    val username: String?,
    val entitlements: List<String>,
)
