package ru.kvartalovea.catscafe.feature.home.impl

import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import ru.kvartalovea.catscafe.core.navigation.api.ScreenProvider
import ru.kvartalovea.catscafe.feature.home.impl.data.repository.HomeRepositoryMock
import ru.kvartalovea.catscafe.feature.home.impl.domain.repository.HomeRepository
import ru.kvartalovea.catscafe.feature.home.impl.domain.usecase.GetNearestBookingUseCase
import ru.kvartalovea.catscafe.feature.home.impl.domain.usecase.GetNewsUseCase
import ru.kvartalovea.catscafe.feature.home.impl.presentation.viewmodel.HomeViewModel

val featureHomeModule = module {
    singleOf(::HomeScreenProvider) { bind<ScreenProvider>() }

    // TODO: заменить на HomeRepositoryImpl(get()) после реализации бэкенда
    single<HomeRepository> { HomeRepositoryMock() }

    factoryOf(::GetNewsUseCase)
    factoryOf(::GetNearestBookingUseCase)

    viewModelOf(::HomeViewModel)
}
