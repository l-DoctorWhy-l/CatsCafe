package ru.kvartalovea.catscafe.feature.catdetails.impl.presentation.model

internal sealed interface CatDetailsUiEvent {
    data object OnBackClick : CatDetailsUiEvent
    data object OnHelpCatClick : CatDetailsUiEvent
    data object OnBookVisitClick : CatDetailsUiEvent
}
