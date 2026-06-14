package com.nikitakrapo.progressif.repositories.tricks

import com.nikitakrapo.progressif.domain.models.Trick
import com.nikitakrapo.progressif.domain.models.TrickDetails
import com.nikitakrapo.progressif.domain.models.TrickInstruction
import com.nikitakrapo.progressif.domain.models.TrickStep

internal fun List<TrickDto>.toTricks(): List<Trick> = map(TrickDto::toTrick)

internal fun TrickDto.toTrick(): Trick = Trick(
    id = id,
    name = name,
)

internal fun TrickDetailsDto.toTrickDetails(): TrickDetails = TrickDetails(
    id = id,
    name = name,
    aliases = aliases.orEmpty(),
    difficulty = difficulty,
    description = description,
    instruction = instruction?.toTrickInstruction(),
    prerequisiteTricks = prerequisiteTricks?.toTricks().orEmpty(),
    nextTricks = nextTricks?.toTricks().orEmpty(),
)

internal fun TrickInstructionDto.toTrickInstruction(): TrickInstruction = TrickInstruction(
    steps = steps.map(TrickStepDto::toTrickStep),
)

internal fun TrickStepDto.toTrickStep(): TrickStep = TrickStep(
    description = description,
)
