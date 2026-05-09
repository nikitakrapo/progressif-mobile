package com.nikitakrapo.progressif.di

import com.nikitakrapo.progressif.kmp.ApplicationContext
import com.russhwolf.settings.NSUserDefaultsSettings
import com.russhwolf.settings.Settings
import platform.Foundation.NSUserDefaults

internal actual fun createSettings(applicationContext: ApplicationContext): Settings =
    NSUserDefaultsSettings(delegate = NSUserDefaults.standardUserDefaults)
