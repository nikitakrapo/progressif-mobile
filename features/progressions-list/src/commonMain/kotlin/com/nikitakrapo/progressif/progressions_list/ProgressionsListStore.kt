package com.nikitakrapo.progressif.progressions_list

import com.arkivanov.mvikotlin.core.store.Store
import com.nikitakrapo.progressif.domain.models.Progression
import com.nikitakrapo.progressif.progressions_list.ProgressionsListStore.Intent
import com.nikitakrapo.progressif.progressions_list.ProgressionsListStore.Label
import com.nikitakrapo.progressif.progressions_list.ProgressionsListStore.State

interface ProgressionsListStore : Store<Intent, State, Label> {

    sealed interface Intent {
    }

    data class State(
        val items: List<Progression>,
    )

    sealed interface Label {
    }
}
