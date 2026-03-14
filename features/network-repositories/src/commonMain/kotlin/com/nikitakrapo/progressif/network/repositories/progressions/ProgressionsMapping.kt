package com.nikitakrapo.progressif.network.repositories.progressions

import com.nikitakrapo.progressif.domain.models.Progression

internal fun ProgressionsDto.toProgressionsList(): List<Progression> {
    return progressions.map(ProgressionDto::toProgression)
}

internal fun ProgressionDto.toProgression(): Progression {
    return Progression(
        id = id,
        name = name,
    )
}