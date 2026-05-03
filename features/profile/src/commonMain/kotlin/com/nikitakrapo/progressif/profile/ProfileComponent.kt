package com.nikitakrapo.progressif.profile

import kotlinx.coroutines.flow.StateFlow

interface ProfileComponent {

    val state: StateFlow<ProfileState>

    fun accept(intent: ProfileStore.Intent)
}
