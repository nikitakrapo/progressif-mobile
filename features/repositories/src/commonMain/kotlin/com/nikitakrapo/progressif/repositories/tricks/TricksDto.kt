package com.nikitakrapo.progressif.repositories.tricks

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class TrickDto(
    @SerialName("slug") val slug: String,
    @SerialName("name") val name: String,
)
