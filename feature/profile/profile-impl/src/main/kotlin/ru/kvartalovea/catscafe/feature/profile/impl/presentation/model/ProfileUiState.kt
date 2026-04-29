package ru.kvartalovea.catscafe.feature.profile.impl.presentation.model

internal sealed interface ProfileUiState {
    data object NoAuth : ProfileUiState
    data class Content(
        val userName: String,
        val userEmail: String,
        val loyaltyPoints: Int,
    ) : ProfileUiState
}
