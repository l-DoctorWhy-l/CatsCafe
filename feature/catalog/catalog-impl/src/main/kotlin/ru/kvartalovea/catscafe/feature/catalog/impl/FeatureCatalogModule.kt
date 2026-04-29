package ru.kvartalovea.catscafe.feature.catalog.impl

import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import ru.kvartalovea.catscafe.core.navigation.api.ScreenProvider
import ru.kvartalovea.catscafe.feature.catalog.impl.data.repository.CatalogRepositoryMock
import ru.kvartalovea.catscafe.feature.catalog.impl.domain.repository.CatalogRepository
import ru.kvartalovea.catscafe.feature.catalog.impl.domain.usecase.GetCatsUseCase
import ru.kvartalovea.catscafe.feature.catalog.impl.presentation.viewmodel.CatalogViewModel

val featureCatalogModule = module {
    singleOf(::CatalogScreenProvider) { bind<ScreenProvider>() }

    // TODO: заменить на реальный репозиторий после реализации бэкенда
    single<CatalogRepository> { CatalogRepositoryMock() }

    factoryOf(::GetCatsUseCase)

    viewModelOf(::CatalogViewModel)
}
