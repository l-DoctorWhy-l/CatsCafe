package ru.kvartalovea.catscafe.feature.helpcat.impl.presentation.model

import ru.kvartalovea.catscafe.common.utils.UiText

internal sealed interface HelpCatUiState {

    data class Content(
        val catName: String,
        val amount: String = "",
        val amountError: UiText? = null,
        val isLoading: Boolean = false,
    ) : HelpCatUiState

    data object Success : HelpCatUiState
}
