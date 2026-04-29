package ru.kvartalovea.catscafe.feature.mybookings.impl

import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import ru.kvartalovea.catscafe.core.navigation.api.ScreenProvider
import ru.kvartalovea.catscafe.feature.mybookings.impl.data.repository.MyBookingsRepositoryMock
import ru.kvartalovea.catscafe.feature.mybookings.impl.domain.repository.MyBookingsRepository
import ru.kvartalovea.catscafe.feature.mybookings.impl.domain.usecase.GetMyBookingsUseCase
import ru.kvartalovea.catscafe.feature.mybookings.impl.presentation.viewmodel.MyBookingsViewModel

val featureMyBookingsModule = module {
    singleOf(::MyBookingsScreenProvider) { bind<ScreenProvider>() }

    single<MyBookingsRepository> { MyBookingsRepositoryMock() }
    singleOf(::GetMyBookingsUseCase)

    viewModelOf(::MyBookingsViewModel)
}
