package com.nikitakrapo.progressif.di

import com.nikitakrapo.progressif.auth.cache.UserCache
import com.nikitakrapo.progressif.auth.remote.UsersService
import com.nikitakrapo.progressif.auth.token.FirebaseAuthTokenProvider
import com.nikitakrapo.progressif.auth.user.FirebaseUserRepository
import com.nikitakrapo.progressif.auth.user.UserRepository
import com.nikitakrapo.progressif.network.AuthTokenProvider
import org.koin.dsl.module

val AuthModule = module {
    single<UsersService> { UsersService(httpClient = get()) }
    single<UserCache> { UserCache(settings = get()) }
    single<UserRepository> {
        FirebaseUserRepository(
            usersService = get(),
            userCache = get(),
        )
    }
    single<AuthTokenProvider> { FirebaseAuthTokenProvider() }
}