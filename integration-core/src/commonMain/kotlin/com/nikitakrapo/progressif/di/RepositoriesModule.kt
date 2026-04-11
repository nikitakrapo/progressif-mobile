package com.nikitakrapo.progressif.di

import com.nikitakrapo.progressif.repositories.progressions.FakeProgressionsRepository
import com.nikitakrapo.progressif.repositories.progressions.ProgressionsRepository
import org.koin.dsl.module

internal val RepositoriesModule = module {
    single<ProgressionsRepository> { FakeProgressionsRepository() }
}
