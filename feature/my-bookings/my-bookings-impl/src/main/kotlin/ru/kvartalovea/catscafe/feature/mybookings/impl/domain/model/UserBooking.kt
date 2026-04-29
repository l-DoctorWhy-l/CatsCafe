package ru.kvartalovea.catscafe.feature.mybookings.impl.domain.model

internal data class UserBooking(
    val id: String,
    val date: String,
    val time: String,
    val guestsCount: Int,
    val status: UserBookingStatus,
)

internal enum class UserBookingStatus { Upcoming, Past, Cancelled }
