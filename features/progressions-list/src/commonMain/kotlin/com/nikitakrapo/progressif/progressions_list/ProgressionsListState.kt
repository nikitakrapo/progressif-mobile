package com.nikitakrapo.progressif.progressions_list

import com.nikitakrapo.progressif.domain.models.Progression
import com.nikitakrapo.progressif.strings.Text

data class ProgressionsListState(
    val items: List<Progression>,
    val isLoading: Boolean,
    val errorText: Text?,
)
