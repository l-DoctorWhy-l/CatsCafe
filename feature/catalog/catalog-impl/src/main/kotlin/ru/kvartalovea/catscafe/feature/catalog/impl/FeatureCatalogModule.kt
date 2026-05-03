package ru.kvartalovea.catscafe.feature.catalog.impl

import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import retrofit2.Retrofit
import ru.kvartalovea.catscafe.core.navigation.api.ScreenProvider
import ru.kvartalovea.catscafe.feature.catalog.impl.data.remote.api.CatalogApiService
import ru.kvartalovea.catscafe.feature.catalog.impl.data.repository.CatalogRepositoryImpl
import ru.kvartalovea.catscafe.feature.catalog.impl.domain.repository.CatalogRepository
import ru.kvartalovea.catscafe.feature.catalog.impl.domain.usecase.GetCatsUseCase
import ru.kvartalovea.catscafe.feature.catalog.impl.presentation.viewmodel.CatalogViewModel

val featureCatalogModule = module {
    singleOf(::CatalogScreenProvider) { bind<ScreenProvider>() }

    single { get<Retrofit>().create(CatalogApiService::class.java) }

    single<CatalogRepository> { CatalogRepositoryImpl(get()) }

    factoryOf(::GetCatsUseCase)

    viewModelOf(::CatalogViewModel)
}
