package com.nikitakrapo.progressif.network.repositories.progressions

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class ProgressionsDto(
    @SerialName("progressions") val progressions: List<ProgressionDto>,
)

@Serializable
internal data class ProgressionDto(
    @SerialName("id") val id: String,
    @SerialName("name") val name: String,
)