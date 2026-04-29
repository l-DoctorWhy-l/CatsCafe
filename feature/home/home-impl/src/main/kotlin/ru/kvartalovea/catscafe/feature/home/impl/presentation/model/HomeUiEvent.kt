package ru.kvartalovea.catscafe.feature.home.impl.presentation.model

internal sealed interface HomeUiEvent {
    data object Refresh : HomeUiEvent
    data class OnNewsClick(val newsId: String) : HomeUiEvent
    data object OnBookingClick : HomeUiEvent
    data object OnNotificationsClick : HomeUiEvent
}
