package com.nikitakrapo.progressif.locale

import platform.Foundation.NSLocale
import platform.Foundation.preferredLanguages

internal actual fun systemLocale(): String =
    (NSLocale.preferredLanguages.firstOrNull() as? String) ?: "en"
