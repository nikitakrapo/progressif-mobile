package com.nikitakrapo.progressif.auth.cache

import com.nikitakrapo.progressif.auth.user.User
import com.russhwolf.settings.Settings
import io.github.aakira.napier.Napier
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

private const val CACHED_USER_KEY = "cached_user"

internal class UserCache(
    private val settings: Settings,
) {

    private val json = Json { ignoreUnknownKeys = true }

    internal fun read(): User? {
        val raw = settings.getStringOrNull(CACHED_USER_KEY) ?: return null
        val cached = try {
            json.decodeFromString<CachedUser>(raw)
        } catch (e: Exception) {
            Napier.e(e) { "Failed to decode cached user; clearing" }
            settings.remove(CACHED_USER_KEY)
            return null
        }
        return cached.toUser()
    }

    internal fun write(user: User) {
        settings.putString(CACHED_USER_KEY, json.encodeToString(user.toCached()))
    }

    internal fun clear() {
        settings.remove(CACHED_USER_KEY)
    }
}

@Serializable
private data class CachedUser(
    @SerialName("id") val id: String,
    @SerialName("email") val email: String?,
    @SerialName("username") val username: String?,
    @SerialName("entitlements") val entitlements: List<String>,
)

private fun CachedUser.toUser() = User(
    id = id,
    email = email,
    username = username,
    entitlements = entitlements,
)

private fun User.toCached() = CachedUser(
    id = id,
    email = email,
    username = username,
    entitlements = entitlements,
)
