package ru.kvartalovea.catscafe.feature.helpcat.impl.presentation.model

internal sealed interface HelpCatUiState {

    data class Content(
        val catName: String,
        val amount: String = "",
        val amountError: String? = null,
        val isLoading: Boolean = false,
    ) : HelpCatUiState

    data object Success : HelpCatUiState
}
