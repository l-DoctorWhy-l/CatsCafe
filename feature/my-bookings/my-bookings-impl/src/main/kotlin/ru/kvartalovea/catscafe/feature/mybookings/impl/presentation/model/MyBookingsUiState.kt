package ru.kvartalovea.catscafe.feature.mybookings.impl.presentation.model

import ru.kvartalovea.catscafe.common.utils.UiText

internal sealed interface MyBookingsUiState {
    data object Loading : MyBookingsUiState
    data class Error(val message: UiText) : MyBookingsUiState
    data class Content(val bookings: List<UserBookingUiModel>) : MyBookingsUiState
}
