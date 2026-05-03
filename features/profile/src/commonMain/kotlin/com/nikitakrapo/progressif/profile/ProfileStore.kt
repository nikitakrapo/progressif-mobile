package com.nikitakrapo.progressif.profile

import com.arkivanov.mvikotlin.core.store.Store
import com.nikitakrapo.progressif.strings.Text

interface ProfileStore : Store<ProfileStore.Intent, ProfileState, ProfileStore.Label> {

    sealed interface Intent {
        data object Refresh : Intent
        data object ShowLogoutConfirmation : Intent
        data object DismissLogoutConfirmation : Intent
        data object AcceptLogoutConfirmation : Intent
    }

    sealed interface Label {
        data class ShowSnackbar(val message: Text) : Label
    }
}
