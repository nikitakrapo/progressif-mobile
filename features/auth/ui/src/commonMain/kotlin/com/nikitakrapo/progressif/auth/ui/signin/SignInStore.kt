package com.nikitakrapo.progressif.auth.ui.signin

import com.arkivanov.mvikotlin.core.store.Store
import com.nikitakrapo.progressif.auth.ui.signin.SignInStore.Intent
import com.nikitakrapo.progressif.auth.ui.signin.SignInStore.Label

interface SignInStore : Store<Intent, SignInState, Label> {

    sealed interface Intent {

        data class EmailChanged(val value: String) : Intent

        data class PasswordChanged(val value: String) : Intent

        data object Submit : Intent
    }

    sealed interface Label {

    }
}