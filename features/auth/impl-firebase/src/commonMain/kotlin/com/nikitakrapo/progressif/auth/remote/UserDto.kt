package com.nikitakrapo.progressif.auth.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class UserDto(
    @SerialName("userId") val userId: String,
    @SerialName("email") val email: String?,
    @SerialName("displayName") val displayName: String?,
    @SerialName("entitlements") val entitlements: List<String>,
)
