package com.nikitakrapo.progressif.tricks

import com.arkivanov.mvikotlin.core.store.Store
import com.nikitakrapo.progressif.strings.Text
import com.nikitakrapo.progressif.tricks.TricksStore.Intent
import com.nikitakrapo.progressif.tricks.TricksStore.Label

interface TricksStore : Store<Intent, TricksState, Label> {

    sealed interface Intent {

        data object Refresh : Intent
    }

    sealed interface Label {

        data class ShowSnackbar(val text: Text) : Label
    }
}
