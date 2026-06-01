package com.nikitakrapo.progressif.di

import com.nikitakrapo.progressif.locale.UserLocaleProvider
import org.koin.dsl.module

internal val LocaleModule = module {
    single<UserLocaleProvider> { UserLocaleProvider() }
}
