package ru.kvartalovea.catscafe.feature.booking.impl.presentation.model

import java.time.LocalDate

internal sealed interface BookingUiEvent {
    data object OnPrevMonthClick : BookingUiEvent
    data object OnNextMonthClick : BookingUiEvent
    data class OnDateSelected(val date: LocalDate) : BookingUiEvent
    data class OnTimeSelected(val time: String) : BookingUiEvent
    data class OnGuestCountSelected(val count: Int) : BookingUiEvent
    data object OnConfirmClick : BookingUiEvent
}
