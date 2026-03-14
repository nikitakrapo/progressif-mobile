package com.nikitakrapo.progressif.di

import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import org.koin.dsl.module

val MviKotlinModule = module {
    single<StoreFactory> { DefaultStoreFactory() }
}