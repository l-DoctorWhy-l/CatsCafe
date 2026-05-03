package ru.kvartalovea.catscafe.feature.booking.impl.domain.repository

import ru.kvartalovea.catscafe.feature.booking.impl.domain.model.TimeSlot

internal interface BookingRepository {
    suspend fun getAvailableSlots(date: String): Result<List<TimeSlot>>
    suspend fun createBooking(date: String, time: String, guestsCount: Int): Result<Unit>
}
