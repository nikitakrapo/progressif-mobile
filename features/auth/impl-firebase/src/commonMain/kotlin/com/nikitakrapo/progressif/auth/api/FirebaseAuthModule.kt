package com.nikitakrapo.progressif.auth.api

import com.nikitakrapo.progressif.auth.cache.UserCache
import com.nikitakrapo.progressif.auth.remote.UsersService
import com.nikitakrapo.progressif.auth.token.FirebaseAuthTokenProvider
import com.nikitakrapo.progressif.auth.user.UserRepository
import com.nikitakrapo.progressif.auth.user.UserRepositoryImpl
import com.nikitakrapo.progressif.firebase.auth.FirebaseAuth
import com.nikitakrapo.progressif.network.AuthTokenProvider
import org.koin.dsl.module

val FirebaseAuthModule = module {
    single<UsersService> { UsersService(httpClient = get()) }
    single<UserCache> { UserCache(settings = get()) }
    single<UserRepository> { UserRepositoryImpl(usersService = get(), userCache = get(), firebaseAuth = FirebaseAuth) }
    single<AuthTokenProvider> { FirebaseAuthTokenProvider() }
}