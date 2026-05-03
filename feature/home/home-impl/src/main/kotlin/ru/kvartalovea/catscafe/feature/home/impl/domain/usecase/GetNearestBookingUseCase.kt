package ru.kvartalovea.catscafe.feature.home.impl.domain.usecase

import ru.kvartalovea.catscafe.feature.home.impl.domain.model.BookingResult
import ru.kvartalovea.catscafe.feature.home.impl.domain.repository.HomeRepository

internal class GetNearestBookingUseCase(private val repository: HomeRepository) {
    suspend operator fun invoke(): Result<BookingResult> = repository.getNearestBooking()
}
