package ru.kvartalovea.catscafe.feature.booking.impl.domain.usecase

import ru.kvartalovea.catscafe.feature.booking.impl.domain.repository.BookingRepository

internal class CreateBookingUseCase(private val repository: BookingRepository) {
    suspend operator fun invoke(date: String, time: String, guestsCount: Int): Result<Unit> =
        repository.createBooking(date, time, guestsCount)
}
