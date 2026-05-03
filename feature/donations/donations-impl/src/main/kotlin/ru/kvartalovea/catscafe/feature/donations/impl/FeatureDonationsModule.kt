package ru.kvartalovea.catscafe.feature.donations.impl

import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import retrofit2.Retrofit
import ru.kvartalovea.catscafe.core.navigation.api.ScreenProvider
import ru.kvartalovea.catscafe.feature.donations.impl.data.remote.api.DonationsApiService
import ru.kvartalovea.catscafe.feature.donations.impl.data.repository.DonationsRepositoryImpl
import ru.kvartalovea.catscafe.feature.donations.impl.domain.repository.DonationsRepository
import ru.kvartalovea.catscafe.feature.donations.impl.domain.usecase.GetDonationsUseCase
import ru.kvartalovea.catscafe.feature.donations.impl.presentation.viewmodel.DonationsViewModel

val featureDonationsModule = module {
    singleOf(::DonationsScreenProvider) { bind<ScreenProvider>() }

    single { get<Retrofit>().create(DonationsApiService::class.java) }

    single<DonationsRepository> { DonationsRepositoryImpl(get()) }
    singleOf(::GetDonationsUseCase)

    viewModelOf(::DonationsViewModel)
}
