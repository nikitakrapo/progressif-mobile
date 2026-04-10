package com.nikitakrapo.progressif.di

import com.nikitakrapo.progressif.network.repositories.progressions.FakeProgressionsRepository
import com.nikitakrapo.progressif.network.repositories.progressions.ProgressionsRepository
import org.koin.dsl.module

internal val RepositoriesModule = module {
    single<ProgressionsRepository> { FakeProgressionsRepository() }
}
