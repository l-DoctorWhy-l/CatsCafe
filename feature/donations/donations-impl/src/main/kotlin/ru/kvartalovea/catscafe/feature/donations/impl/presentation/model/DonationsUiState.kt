package ru.kvartalovea.catscafe.feature.donations.impl.presentation.model

import ru.kvartalovea.catscafe.common.utils.UiText

internal sealed interface DonationsUiState {
    data object Loading : DonationsUiState
    data class Error(val message: UiText) : DonationsUiState
    data class Content(val donations: List<DonationUiModel>) : DonationsUiState
}
