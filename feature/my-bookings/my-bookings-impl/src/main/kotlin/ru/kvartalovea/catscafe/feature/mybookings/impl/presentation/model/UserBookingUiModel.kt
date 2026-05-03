package ru.kvartalovea.catscafe.feature.mybookings.impl.presentation.model

import androidx.annotation.StringRes
import ru.kvartalovea.catscafe.feature.mybookings.impl.R

internal data class UserBookingUiModel(
    val id: String,
    val date: String,
    val time: String,
    val guestsCount: Int,
    val status: BookingStatusUiModel,
)

internal enum class BookingStatusUiModel(@StringRes val labelRes: Int) {
    Upcoming(R.string.my_bookings_status_upcoming),
    Past(R.string.my_bookings_status_past),
    Cancelled(R.string.my_bookings_status_cancelled),
}
