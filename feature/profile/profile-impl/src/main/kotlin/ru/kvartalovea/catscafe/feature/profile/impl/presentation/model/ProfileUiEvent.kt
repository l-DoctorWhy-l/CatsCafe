package ru.kvartalovea.catscafe.feature.profile.impl.presentation.model

internal sealed interface ProfileUiEvent {
    data object OnMyBookingsClick : ProfileUiEvent
    data object OnDonationsHistoryClick : ProfileUiEvent
    data object OnNotificationSettingsClick : ProfileUiEvent
    data object OnLogoutClick : ProfileUiEvent
}
