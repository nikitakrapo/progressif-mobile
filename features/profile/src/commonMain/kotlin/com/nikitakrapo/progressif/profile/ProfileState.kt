package com.nikitakrapo.progressif.profile

data class ProfileState(
    val username: String = "",
    val sections: List<ProfileSection> = emptyList(),
    val isLoading: Boolean = false,
    val isLogoutConfirmationShown: Boolean = false,
)
