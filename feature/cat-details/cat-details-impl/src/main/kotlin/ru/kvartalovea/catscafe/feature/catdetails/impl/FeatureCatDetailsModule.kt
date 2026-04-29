package ru.kvartalovea.catscafe.feature.catdetails.impl

import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import ru.kvartalovea.catscafe.core.navigation.api.ScreenProvider
import ru.kvartalovea.catscafe.feature.catdetails.impl.data.repository.CatDetailsRepositoryMock
import ru.kvartalovea.catscafe.feature.catdetails.impl.domain.repository.CatDetailsRepository
import ru.kvartalovea.catscafe.feature.catdetails.impl.domain.usecase.GetCatDetailsUseCase
import ru.kvartalovea.catscafe.feature.catdetails.impl.presentation.viewmodel.CatDetailsViewModel

val featureCatDetailsModule = module {
    singleOf(::CatDetailsScreenProvider) { bind<ScreenProvider>() }

    single<CatDetailsRepository> { CatDetailsRepositoryMock() }
    singleOf(::GetCatDetailsUseCase)

    viewModel { params ->
        CatDetailsViewModel(
            catId = params.get(),
            getCatDetailsUseCase = get(),
            navigator = get(),
        )
    }
}
