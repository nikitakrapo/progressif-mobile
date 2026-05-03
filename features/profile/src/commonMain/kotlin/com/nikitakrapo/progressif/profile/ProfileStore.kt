package com.nikitakrapo.progressif.profile

import com.arkivanov.mvikotlin.core.store.Store

interface ProfileStore : Store<ProfileStore.Intent, ProfileState, ProfileStore.Label> {

    sealed interface Intent {
        data object Refresh : Intent

        data object DismissLogoutConfirmation : Intent

        data object AcceptLogoutConfirmation : Intent
    }

    sealed interface Label
}
