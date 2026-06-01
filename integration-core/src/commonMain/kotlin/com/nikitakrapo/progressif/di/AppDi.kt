package com.nikitakrapo.progressif.di

import com.nikitakrapo.progressif.kmp.ApplicationContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

object AppDi {

    fun start(applicationContext: ApplicationContext) {
        startKoin {
            modules(
                module { single<ApplicationContext> { applicationContext } },
                SettingsModule,
                LocaleModule,
                NetworkModule,
                AuthModule,
                RepositoriesModule,
                MviKotlinModule,
            )
        }
    }
}
