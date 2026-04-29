package ru.kvartalovea.catscafe.feature.mybookings.impl.presentation.model

internal sealed interface MyBookingsUiState {
    data object Loading : MyBookingsUiState
    data class Error(val message: String) : MyBookingsUiState
    data class Content(val bookings: List<UserBookingUiModel>) : MyBookingsUiState
}
