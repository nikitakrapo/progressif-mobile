package com.nikitakrapo.progressif.tricks

import com.nikitakrapo.progressif.domain.models.Trick
import com.nikitakrapo.progressif.strings.Text

data class TricksState(
    val tricks: List<Trick>,
    val isLoading: Boolean,
    val errorText: Text?,
)
