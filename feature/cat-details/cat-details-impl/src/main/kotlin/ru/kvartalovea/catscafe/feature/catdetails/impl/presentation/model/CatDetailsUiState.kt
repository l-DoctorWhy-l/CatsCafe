package ru.kvartalovea.catscafe.feature.catdetails.impl.presentation.model

internal sealed interface CatDetailsUiState {
    data object Loading : CatDetailsUiState
    data object NotFound : CatDetailsUiState
    data class Content(val cat: CatDetailUiModel) : CatDetailsUiState
}
