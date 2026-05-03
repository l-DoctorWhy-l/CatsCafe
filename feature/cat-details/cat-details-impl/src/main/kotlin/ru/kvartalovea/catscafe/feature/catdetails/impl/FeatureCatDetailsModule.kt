package ru.kvartalovea.catscafe.feature.catdetails.impl

import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import ru.kvartalovea.catscafe.core.navigation.api.ScreenProvider
import ru.kvartalovea.catscafe.feature.catdetails.impl.data.remote.api.CatDetailsApiService
import ru.kvartalovea.catscafe.feature.catdetails.impl.data.repository.CatDetailsRepositoryImpl
import ru.kvartalovea.catscafe.feature.catdetails.impl.domain.repository.CatDetailsRepository
import ru.kvartalovea.catscafe.feature.catdetails.impl.domain.usecase.GetCatDetailsUseCase
import ru.kvartalovea.catscafe.feature.catdetails.impl.presentation.viewmodel.CatDetailsViewModel

val featureCatDetailsModule = module {
    singleOf(::CatDetailsScreenProvider) { bind<ScreenProvider>() }

    single { get<Retrofit>().create(CatDetailsApiService::class.java) }

    single<CatDetailsRepository> { CatDetailsRepositoryImpl(get()) }
    singleOf(::GetCatDetailsUseCase)

    viewModel { params ->
        CatDetailsViewModel(
            catId = params.get(),
            getCatDetailsUseCase = get(),
            navigator = get(),
        )
    }
}
