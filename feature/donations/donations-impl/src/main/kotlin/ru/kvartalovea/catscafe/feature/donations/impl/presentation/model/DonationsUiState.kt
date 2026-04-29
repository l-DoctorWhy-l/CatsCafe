package ru.kvartalovea.catscafe.feature.donations.impl.presentation.model

internal sealed interface DonationsUiState {
    data object Loading : DonationsUiState
    data class Error(val message: String) : DonationsUiState
    data class Content(val donations: List<DonationUiModel>) : DonationsUiState
}
