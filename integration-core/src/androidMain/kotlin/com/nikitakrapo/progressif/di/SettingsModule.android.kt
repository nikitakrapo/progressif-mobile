package com.nikitakrapo.progressif.di

import android.content.Context
import com.nikitakrapo.progressif.kmp.ApplicationContext
import com.russhwolf.settings.SharedPreferencesSettings
import com.russhwolf.settings.Settings

private const val PREFS_NAME = "progressif_settings"

internal actual fun createSettings(applicationContext: ApplicationContext): Settings =
    SharedPreferencesSettings(
        delegate = applicationContext.context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE),
    )
