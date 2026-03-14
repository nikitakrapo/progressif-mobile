package com.nikitakrapo.progressif.di

import org.koin.core.context.startKoin

object AppDi {

    fun start() {
        startKoin {
            modules(
                NetworkModule,
                RepositoriesModule,
            )
        }
    }
}