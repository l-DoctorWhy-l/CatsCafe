package ru.kvartalovea.catscafe.feature.booking.impl.presentation.model

internal sealed interface BookingUiState {
    data class Content(
        val monthTitle: String,
        val isPrevMonthEnabled: Boolean,
        val calendarDays: List<CalendarDayUiModel?>,
        val timeSlots: List<TimeSlotUiModel>,
        val guestCounts: List<Int>,
        val selectedGuestCount: Int,
        val isConfirmEnabled: Boolean,
    ) : BookingUiState

    data object Success : BookingUiState
}
