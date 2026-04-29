package ru.kvartalovea.catscafe.feature.mybookings.impl.presentation.model

internal data class UserBookingUiModel(
    val id: String,
    val date: String,
    val time: String,
    val guestsCount: Int,
    val status: BookingStatusUiModel,
)

internal enum class BookingStatusUiModel(val label: String) {
    Upcoming("Предстоит"),
    Past("Прошло"),
    Cancelled("Отменено"),
}
