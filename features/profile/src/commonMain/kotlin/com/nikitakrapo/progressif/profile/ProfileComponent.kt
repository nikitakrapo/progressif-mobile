package com.nikitakrapo.progressif.profile

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface ProfileComponent {

    val state: StateFlow<ProfileState>

    val events: Flow<ProfileStore.Label>

    fun accept(intent: ProfileStore.Intent)
}
