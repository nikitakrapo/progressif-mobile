package com.nikitakrapo.progressif.auth.remote

import com.nikitakrapo.progressif.auth.user.User
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class UserDto(
    @SerialName("userId") val userId: String,
    @SerialName("email") val email: String?,
    @SerialName("displayName") val displayName: String?,
    @SerialName("entitlements") val entitlements: List<String>,
)

internal fun UserDto.toUser(): User = User(
    id = userId,
    email = email,
    displayName = displayName,
    entitlements = entitlements,
)
