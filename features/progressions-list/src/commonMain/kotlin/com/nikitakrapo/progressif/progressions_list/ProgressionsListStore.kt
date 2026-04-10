package com.nikitakrapo.progressif.progressions_list

import com.arkivanov.mvikotlin.core.store.Store
import com.nikitakrapo.progressif.domain.models.Progression
import com.nikitakrapo.progressif.progressions_list.ProgressionsListStore.Intent
import com.nikitakrapo.progressif.progressions_list.ProgressionsListStore.Label
import com.nikitakrapo.progressif.strings.Text

interface ProgressionsListStore : Store<Intent, ProgressionsListState, Label> {

    sealed interface Intent {

        data object Refresh : Intent

        data class ProgressionClick(val progression: Progression) : Intent

        data object AddProgressionClick : Intent
    }

    sealed interface Label {

        data class ShowSnackbar(val text: Text) : Label

        data class OpenProgressionDetails(val progression: Progression) : Label

        data object OpenAddProgression : Label
    }
}
