package com.nikitakrapo.progressif.di

import com.nikitakrapo.progressif.auth.user.UserRepository
import com.nikitakrapo.progressif.auth.user.UserRepositoryImpl
import org.koin.dsl.module

val AuthModule = module {
    single<UserRepository> { UserRepositoryImpl() }
}