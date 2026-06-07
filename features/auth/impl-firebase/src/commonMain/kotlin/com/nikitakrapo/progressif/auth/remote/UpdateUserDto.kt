package com.nikitakrapo.progressif.auth.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class UpdateUserDto(
    @SerialName("username") val username: String,
)