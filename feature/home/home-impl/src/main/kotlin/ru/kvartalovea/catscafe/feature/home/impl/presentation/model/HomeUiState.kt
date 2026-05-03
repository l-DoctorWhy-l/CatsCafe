package ru.kvartalovea.catscafe.feature.home.impl.presentation.model

import ru.kvartalovea.catscafe.common.utils.UiText

internal sealed interface HomeUiState {
    data object Loading : HomeUiState
    data class Error(val message: UiText) : HomeUiState
    data class Content(
        val userName: String = "",
        val nearestBooking: NearestBookingUiModel?,
        val newsList: List<NewsItemUiModel>,
        val isOffline: Boolean = false,
        val showQrDialog: Boolean = false,
    ) : HomeUiState
}
