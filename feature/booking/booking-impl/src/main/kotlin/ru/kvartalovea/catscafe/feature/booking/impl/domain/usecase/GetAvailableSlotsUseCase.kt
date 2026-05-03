package ru.kvartalovea.catscafe.feature.booking.impl.domain.usecase

import ru.kvartalovea.catscafe.feature.booking.impl.domain.model.TimeSlot
import ru.kvartalovea.catscafe.feature.booking.impl.domain.repository.BookingRepository

internal class GetAvailableSlotsUseCase(private val repository: BookingRepository) {
    suspend operator fun invoke(date: String): Result<List<TimeSlot>> =
        repository.getAvailableSlots(date)
}
