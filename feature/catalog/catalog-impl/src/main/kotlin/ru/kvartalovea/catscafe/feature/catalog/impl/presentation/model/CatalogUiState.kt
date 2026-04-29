package ru.kvartalovea.catscafe.feature.catalog.impl.presentation.model

internal sealed interface CatalogUiState {
    data object Loading : CatalogUiState
    data class Error(val message: String) : CatalogUiState
    data class Content(
        val filteredCats: List<CatUiModel>,
        val activeFilter: CatFilter,
        val searchQuery: String,
    ) : CatalogUiState
}
