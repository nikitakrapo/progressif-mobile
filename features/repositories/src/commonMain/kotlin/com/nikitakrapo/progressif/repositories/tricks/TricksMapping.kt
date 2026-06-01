package com.nikitakrapo.progressif.repositories.tricks

import com.nikitakrapo.progressif.domain.models.Trick

internal fun List<TrickDto>.toTricks(): List<Trick> = map(TrickDto::toTrick)

internal fun TrickDto.toTrick(): Trick = Trick(
    slug = slug,
    name = name,
)
