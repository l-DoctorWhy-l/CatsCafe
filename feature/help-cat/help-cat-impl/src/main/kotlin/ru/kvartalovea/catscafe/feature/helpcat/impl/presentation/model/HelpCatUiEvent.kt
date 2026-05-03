package ru.kvartalovea.catscafe.feature.helpcat.impl.presentation.model

internal sealed interface HelpCatUiEvent {
    data object OnBackClick : HelpCatUiEvent
    data class OnAmountChanged(val value: String) : HelpCatUiEvent
    data object OnDonateClick : HelpCatUiEvent
}
