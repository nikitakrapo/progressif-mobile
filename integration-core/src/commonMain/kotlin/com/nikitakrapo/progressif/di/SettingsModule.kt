package com.nikitakrapo.progressif.di

import com.nikitakrapo.progressif.kmp.ApplicationContext
import com.russhwolf.settings.Settings
import org.koin.dsl.module

internal expect fun createSettings(applicationContext: ApplicationContext): Settings

internal val SettingsModule = module {
    single<Settings> { createSettings(applicationContext = get()) }
}
