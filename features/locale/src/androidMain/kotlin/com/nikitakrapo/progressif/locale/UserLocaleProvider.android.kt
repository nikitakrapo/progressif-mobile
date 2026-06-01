package com.nikitakrapo.progressif.locale

import java.util.Locale

internal actual fun systemLocale(): String = Locale.getDefault().toLanguageTag()
