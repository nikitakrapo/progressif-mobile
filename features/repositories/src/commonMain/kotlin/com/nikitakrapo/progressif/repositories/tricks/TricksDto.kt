package com.nikitakrapo.progressif.repositories.tricks

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class TrickDto(
    @SerialName("id") val id: String,
    @SerialName("name") val name: String,
)

@Serializable
internal data class TrickDetailsDto(
    @SerialName("id") val id: String,
    @SerialName("name") val name: String,
    @SerialName("aliases") val aliases: List<String>? = null,
    @SerialName("difficulty") val difficulty: String? = null,
    @SerialName("description") val description: String? = null,
    @SerialName("instruction") val instruction: TrickInstructionDto? = null,
    @SerialName("prerequisiteTricks") val prerequisiteTricks: List<TrickDto>? = null,
    @SerialName("nextTricks") val nextTricks: List<TrickDto>? = null,
)

@Serializable
internal data class TrickInstructionDto(
    @SerialName("steps") val steps: List<TrickStepDto>,
)

@Serializable
internal data class TrickStepDto(
    @SerialName("description") val description: String,
)
