package com.nikitakrapo.progressif.auth.cache

import com.nikitakrapo.progressif.auth.user.User
import com.russhwolf.settings.Settings
import io.github.aakira.napier.Napier
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

private const val CACHED_USER_KEY = "cached_user"

class UserCache(
    private val settings: Settings,
) {

    private val json = Json { ignoreUnknownKeys = true }

    internal fun read(firebaseUid: String): User? {
        val raw = settings.getStringOrNull(CACHED_USER_KEY) ?: return null
        val cached = try {
            json.decodeFromString<CachedUser>(raw)
        } catch (e: Exception) {
            Napier.e(e) { "Failed to decode cached user; clearing" }
            settings.remove(CACHED_USER_KEY)
            return null
        }
        return if (cached.firebaseUid == firebaseUid) {
            cached.toUser()
        } else {
            clear()
            null
        }
    }

    internal fun write(firebaseUid: String, user: User) {
        settings.putString(CACHED_USER_KEY, json.encodeToString(user.toCached(firebaseUid)))
    }

    internal fun clear() {
        settings.remove(CACHED_USER_KEY)
    }
}

@Serializable
private data class CachedUser(
    @SerialName("firebaseUid") val firebaseUid: String,
    @SerialName("id") val id: String,
    @SerialName("email") val email: String?,
    @SerialName("displayName") val displayName: String?,
    @SerialName("entitlements") val entitlements: List<String>,
)

private fun CachedUser.toUser() = User(
    id = id,
    email = email,
    displayName = displayName,
    entitlements = entitlements,
)

private fun User.toCached(firebaseUid: String) = CachedUser(
    firebaseUid = firebaseUid,
    id = id,
    email = email,
    displayName = displayName,
    entitlements = entitlements,
)
