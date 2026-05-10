package com.nikitakrapo.progressif.auth.ui.registration

import com.arkivanov.mvikotlin.core.store.Store
import com.nikitakrapo.progressif.auth.ui.registration.RegistrationStore.Intent
import com.nikitakrapo.progressif.auth.ui.registration.RegistrationStore.Label

interface RegistrationStore : Store<Intent, RegistrationState, Label> {

    sealed interface Intent {

        data class UsernameChanged(val value: String) : Intent

        data class EmailChanged(val value: String) : Intent

        data class PasswordChanged(val value: String) : Intent

        data object Submit : Intent
    }

    sealed interface Label {

    }
}
