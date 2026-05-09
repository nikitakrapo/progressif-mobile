package com.nikitakrapo.progressif.di

import com.nikitakrapo.progressif.auth.token.FirebaseAuthTokenProvider
import com.nikitakrapo.progressif.auth.user.FirebaseUserRepository
import com.nikitakrapo.progressif.auth.user.UserRepository
import com.nikitakrapo.progressif.network.AuthTokenProvider
import org.koin.dsl.module

val AuthModule = module {
    single<UserRepository> { FirebaseUserRepository() }
    single<AuthTokenProvider> { FirebaseAuthTokenProvider() }
}