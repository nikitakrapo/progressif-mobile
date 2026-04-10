package com.nikitakrapo.progressif.progressions_list

import com.nikitakrapo.progressif.domain.models.Progression
import com.nikitakrapo.progressif.strings.Text

data class ProgressionsListState(
    val items: List<ProgressionsListItem>,
    val isLoading: Boolean,
    val errorText: Text?,
)

sealed interface ProgressionsListItem {

    data class ProgressionItem(val progression: Progression) : ProgressionsListItem

    data object AddProgressionItem : ProgressionsListItem
}
