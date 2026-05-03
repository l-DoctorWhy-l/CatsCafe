package ru.kvartalovea.catscafe.feature.catalog.impl.presentation.model

import ru.kvartalovea.catscafe.common.utils.UiText

internal sealed interface CatalogUiState {
    data object Loading : CatalogUiState
    data class Error(val message: UiText) : CatalogUiState
    data class Content(
        val filteredCats: List<CatUiModel>,
        val activeFilter: CatFilter,
        val searchQuery: String,
    ) : CatalogUiState
}
