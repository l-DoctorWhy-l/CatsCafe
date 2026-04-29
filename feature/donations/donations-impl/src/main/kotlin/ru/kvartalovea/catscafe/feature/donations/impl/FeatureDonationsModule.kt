package ru.kvartalovea.catscafe.feature.donations.impl

import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import ru.kvartalovea.catscafe.core.navigation.api.ScreenProvider
import ru.kvartalovea.catscafe.feature.donations.impl.data.repository.DonationsRepositoryMock
import ru.kvartalovea.catscafe.feature.donations.impl.domain.repository.DonationsRepository
import ru.kvartalovea.catscafe.feature.donations.impl.domain.usecase.GetDonationsUseCase
import ru.kvartalovea.catscafe.feature.donations.impl.presentation.viewmodel.DonationsViewModel

val featureDonationsModule = module {
    singleOf(::DonationsScreenProvider) { bind<ScreenProvider>() }

    single<DonationsRepository> { DonationsRepositoryMock() }
    singleOf(::GetDonationsUseCase)

    viewModelOf(::DonationsViewModel)
}
