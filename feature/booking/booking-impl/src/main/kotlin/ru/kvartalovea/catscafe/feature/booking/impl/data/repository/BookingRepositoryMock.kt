package ru.kvartalovea.catscafe.feature.booking.impl.data.repository

import kotlinx.coroutines.delay
import ru.kvartalovea.catscafe.feature.booking.impl.domain.model.TimeSlot
import ru.kvartalovea.catscafe.feature.booking.impl.domain.repository.BookingRepository

internal class BookingRepositoryMock : BookingRepository {

    override suspend fun getAvailableSlots(date: String): Result<List<TimeSlot>> {
        delay(MOCK_DELAY_MS)
        return Result.success(
            ALL_TIMES.map { time ->
                TimeSlot(time = time, isAvailable = time !in UNAVAILABLE_TIMES)
            },
        )
    }

    override suspend fun createBooking(
        date: String,
        time: String,
        guestsCount: Int,
    ): Result<Unit> {
        delay(MOCK_DELAY_MS)
        return Result.success(Unit)
    }

    private companion object {
        const val MOCK_DELAY_MS = 400L

        val ALL_TIMES = listOf(
            "10:00", "11:00", "12:00", "13:00", "14:00",
            "15:00", "16:00", "17:00", "18:00", "19:00",
        )

        // Simulate already-booked slots
        val UNAVAILABLE_TIMES = setOf("12:00", "16:00")
    }
}
