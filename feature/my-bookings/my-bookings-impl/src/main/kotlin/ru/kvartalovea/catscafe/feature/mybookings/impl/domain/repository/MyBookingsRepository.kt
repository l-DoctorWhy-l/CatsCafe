package ru.kvartalovea.catscafe.feature.mybookings.impl.domain.repository

import ru.kvartalovea.catscafe.feature.mybookings.impl.domain.model.UserBooking

internal interface MyBookingsRepository {
    suspend fun getMyBookings(): Result<List<UserBooking>>
}
