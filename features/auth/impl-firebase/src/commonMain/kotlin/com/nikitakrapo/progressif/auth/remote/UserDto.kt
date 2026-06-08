package com.nikitakrapo.progressif.auth.remote

import com.nikitakrapo.progressif.auth.user.User
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class UserDto(
    @SerialName("userId") val userId: String,
    @SerialName("email") val email: String?,
    @SerialName("username") val username: String?,
    @SerialName("entitlements") val entitlements: List<String>,
    @SerialName("passedOnboarding") val passedOnboarding: Boolean = false,
)

internal fun UserDto.toUser(): User = User(
    id = userId,
    email = email,
    username = username,
    entitlements = entitlements,
    passedOnboarding = passedOnboarding,
)
