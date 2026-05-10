package com.nikitakrapo.progressif.auth.ui.login

import com.arkivanov.mvikotlin.core.store.Store
import com.nikitakrapo.progressif.auth.ui.login.LoginStore.Intent
import com.nikitakrapo.progressif.auth.ui.login.LoginStore.Label

interface LoginStore : Store<Intent, LoginState, Label> {

    sealed interface Intent {

        data class EmailChanged(val value: String) : Intent

        data class PasswordChanged(val value: String) : Intent

        data object Submit : Intent
    }

    sealed interface Label {

    }
}
