package ru.kvartalovea.catscafe.feature.helpcat.impl

import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import retrofit2.Retrofit
import ru.kvartalovea.catscafe.core.navigation.api.ScreenProvider
import ru.kvartalovea.catscafe.feature.helpcat.impl.data.remote.api.HelpCatApiService
import ru.kvartalovea.catscafe.feature.helpcat.impl.data.repository.HelpCatRepositoryImpl
import ru.kvartalovea.catscafe.feature.helpcat.impl.domain.repository.HelpCatRepository
import ru.kvartalovea.catscafe.feature.helpcat.impl.domain.usecase.MakeDonationUseCase
import ru.kvartalovea.catscafe.feature.helpcat.impl.presentation.viewmodel.HelpCatViewModel

val featureHelpCatModule = module {
    singleOf(::HelpCatScreenProvider) { bind<ScreenProvider>() }

    single { get<Retrofit>().create(HelpCatApiService::class.java) }
    single<HelpCatRepository> { HelpCatRepositoryImpl(get()) }
    factoryOf(::MakeDonationUseCase)

    factory { params ->
        HelpCatViewModel(
            catId = params.get(0),
            catName = params.get(1),
            makeDonationUseCase = get(),
            navigator = get(),
        )
    }
}
