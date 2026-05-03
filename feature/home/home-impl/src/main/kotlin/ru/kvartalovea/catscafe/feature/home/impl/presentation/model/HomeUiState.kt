package ru.kvartalovea.catscafe.feature.home.impl.presentation.model

internal sealed interface HomeUiState {
    data object Loading : HomeUiState
    data class Error(val message: String) : HomeUiState
    data class Content(
        val userName: String = "",
        val nearestBooking: NearestBookingUiModel?,
        val newsList: List<NewsItemUiModel>,
        val isOffline: Boolean = false,
    ) : HomeUiState
}
