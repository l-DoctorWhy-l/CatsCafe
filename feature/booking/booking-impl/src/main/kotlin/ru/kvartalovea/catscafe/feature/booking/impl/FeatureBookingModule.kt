package ru.kvartalovea.catscafe.feature.booking.impl

import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import ru.kvartalovea.catscafe.core.navigation.api.ScreenProvider
import ru.kvartalovea.catscafe.feature.booking.impl.data.remote.api.BookingApiService
import ru.kvartalovea.catscafe.feature.booking.impl.data.repository.BookingRepositoryImpl
import ru.kvartalovea.catscafe.feature.booking.impl.domain.repository.BookingRepository
import ru.kvartalovea.catscafe.feature.booking.impl.domain.usecase.CreateBookingUseCase
import ru.kvartalovea.catscafe.feature.booking.impl.domain.usecase.GetAvailableSlotsUseCase
import ru.kvartalovea.catscafe.feature.booking.impl.presentation.viewmodel.BookingViewModel

val featureBookingModule = module {
    singleOf(::BookingScreenProvider) { bind<ScreenProvider>() }

    single { get<Retrofit>().create(BookingApiService::class.java) }

    single<BookingRepository> { BookingRepositoryImpl(get()) }
    singleOf(::GetAvailableSlotsUseCase)
    singleOf(::CreateBookingUseCase)

    viewModel { params ->
        BookingViewModel(
            preselectedCatId = params.getOrNull<String>(),
            getAvailableSlotsUseCase = get(),
            createBookingUseCase = get(),
            navigator = get(),
        )
    }
}
