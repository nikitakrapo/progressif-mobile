package com.nikitakrapo.progressif.locale

/**
 * Holds the user's locale. For now it always returns the system locale;
 * structured so a user-selected override can be added later.
 */
class UserLocaleProvider {

    /** BCP-47 language tag, e.g. "en-US". */
    val locale: String get() = systemLocale()
}

internal expect fun systemLocale(): String
