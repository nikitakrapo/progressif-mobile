package com.nikitakrapo.progressif.di

import com.nikitakrapo.progressif.repositories.progressions.FakeProgressionsRepository
import com.nikitakrapo.progressif.repositories.progressions.ProgressionsRepository
import com.nikitakrapo.progressif.repositories.tricks.TricksRepository
import com.nikitakrapo.progressif.repositories.tricks.TricksRepositoryImpl
import org.koin.dsl.module

internal val RepositoriesModule = module {
    single<ProgressionsRepository> { FakeProgressionsRepository() }
    single<TricksRepository> { TricksRepositoryImpl(httpClient = get()) }
}
