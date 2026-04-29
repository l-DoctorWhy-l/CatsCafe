package ru.kvartalovea.catscafe.feature.mybookings.impl.presentation.model

internal sealed interface MyBookingsUiEvent {
    data object OnBackClick : MyBookingsUiEvent
    data object Refresh : MyBookingsUiEvent
}
