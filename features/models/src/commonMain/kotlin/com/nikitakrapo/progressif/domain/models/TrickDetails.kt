package com.nikitakrapo.progressif.domain.models

data class TrickDetails(
    val id: String,
    val name: String,
    val aliases: List<String>,
    val difficulty: String?,
    val description: String?,
    val instruction: TrickInstruction?,
    val prerequisiteTricks: List<Trick>,
    val nextTricks: List<Trick>,
)

data class TrickInstruction(
    val steps: List<TrickStep>,
)

data class TrickStep(
    val description: String,
)
