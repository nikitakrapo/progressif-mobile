package com.nikitakrapo.progressif.profile

import com.nikitakrapo.progressif.strings.Text

sealed interface ProfileSection {
    data class Header(val displayName: String) : ProfileSection
    data class Button(val label: Text, val intent: ProfileStore.Intent) : ProfileSection
}
