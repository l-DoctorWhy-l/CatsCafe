package ru.kvartalovea.catscafe.feature.donations.impl.presentation.model

internal sealed interface DonationsUiEvent {
    data object OnBackClick : DonationsUiEvent
    data object Refresh : DonationsUiEvent
}
