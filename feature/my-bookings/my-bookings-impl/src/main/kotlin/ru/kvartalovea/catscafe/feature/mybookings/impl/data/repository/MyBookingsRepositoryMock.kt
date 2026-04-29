package ru.kvartalovea.catscafe.feature.mybookings.impl.data.repository

import kotlinx.coroutines.delay
import ru.kvartalovea.catscafe.feature.mybookings.impl.domain.model.UserBooking
import ru.kvartalovea.catscafe.feature.mybookings.impl.domain.model.UserBookingStatus
import ru.kvartalovea.catscafe.feature.mybookings.impl.domain.repository.MyBookingsRepository

internal class MyBookingsRepositoryMock : MyBookingsRepository {

    override suspend fun getMyBookings(): Result<List<UserBooking>> {
        delay(MOCK_DELAY_MS)
        return Result.success(MOCK_BOOKINGS)
    }

    private companion object {
        const val MOCK_DELAY_MS = 500L

        val MOCK_BOOKINGS = listOf(
            UserBooking(
                id = "b1",
                date = "15 мая 2026",
                time = "14:00",
                guestsCount = 2,
                status = UserBookingStatus.Upcoming,
            ),
            UserBooking(
                id = "b2",
                date = "10 апреля 2026",
                time = "16:30",
                guestsCount = 3,
                status = UserBookingStatus.Past,
            ),
            UserBooking(
                id = "b3",
                date = "1 марта 2026",
                time = "12:00",
                guestsCount = 1,
                status = UserBookingStatus.Cancelled,
            ),
        )
    }
}
