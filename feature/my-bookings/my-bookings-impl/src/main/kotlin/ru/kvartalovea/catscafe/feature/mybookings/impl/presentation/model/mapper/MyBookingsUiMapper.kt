package ru.kvartalovea.catscafe.feature.mybookings.impl.presentation.model.mapper

import ru.kvartalovea.catscafe.feature.mybookings.impl.domain.model.UserBooking
import ru.kvartalovea.catscafe.feature.mybookings.impl.domain.model.UserBookingStatus
import ru.kvartalovea.catscafe.feature.mybookings.impl.presentation.model.BookingStatusUiModel
import ru.kvartalovea.catscafe.feature.mybookings.impl.presentation.model.UserBookingUiModel

internal fun UserBooking.toUiModel() = UserBookingUiModel(
    id = id,
    date = date,
    time = time,
    guestsCount = guestsCount,
    status = status.toUiModel(),
)

internal fun UserBookingStatus.toUiModel() = when (this) {
    UserBookingStatus.Upcoming -> BookingStatusUiModel.Upcoming
    UserBookingStatus.Past -> BookingStatusUiModel.Past
    UserBookingStatus.Cancelled -> BookingStatusUiModel.Cancelled
}

internal fun List<UserBooking>.toUiModels() = map { it.toUiModel() }
