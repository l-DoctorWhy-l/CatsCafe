package ru.kvartalovea.catscafe.feature.catalog.impl.presentation.model

internal sealed interface CatalogUiEvent {
    data class OnSearchQueryChanged(val query: String) : CatalogUiEvent
    data class OnFilterSelected(val filter: CatFilter) : CatalogUiEvent
    data class OnCatClicked(val catId: String) : CatalogUiEvent
    data object Refresh : CatalogUiEvent
}
