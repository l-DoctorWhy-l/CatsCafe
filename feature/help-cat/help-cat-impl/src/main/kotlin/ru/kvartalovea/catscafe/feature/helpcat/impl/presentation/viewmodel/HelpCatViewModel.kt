package ru.kvartalovea.catscafe.feature.helpcat.impl.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.kvartalovea.catscafe.core.navigation.api.AppNavigator
import ru.kvartalovea.catscafe.feature.helpcat.impl.domain.usecase.MakeDonationUseCase
import ru.kvartalovea.catscafe.feature.helpcat.impl.presentation.model.HelpCatUiEvent
import ru.kvartalovea.catscafe.feature.helpcat.impl.presentation.model.HelpCatUiState

internal class HelpCatViewModel(
    private val catId: String,
    catName: String,
    private val makeDonationUseCase: MakeDonationUseCase,
    private val navigator: AppNavigator,
) : ViewModel() {

    private val _state = MutableStateFlow<HelpCatUiState>(HelpCatUiState.Content(catName = catName))
    val state: StateFlow<HelpCatUiState> = _state.asStateFlow()

    fun onEvent(event: HelpCatUiEvent) {
        when (event) {
            HelpCatUiEvent.OnBackClick -> navigator.popBackStack()
            is HelpCatUiEvent.OnAmountChanged -> updateContent { copy(amount = event.value, amountError = null) }
            HelpCatUiEvent.OnDonateClick -> donate()
        }
    }

    private fun donate() {
        val s = _state.value as? HelpCatUiState.Content ?: return
        val amountValue = s.amount.trim().toIntOrNull()
        if (amountValue == null || amountValue <= 0) {
            updateContent { copy(amountError = "Введите корректную сумму") }
            return
        }
        updateContent { copy(isLoading = true, amountError = null) }
        viewModelScope.launch {
            makeDonationUseCase(catId = catId, amount = amountValue)
                .onSuccess {
                    _state.value = HelpCatUiState.Success
                    delay(SUCCESS_DISPLAY_MS)
                    navigator.popBackStack()
                }
                .onFailure { error ->
                    updateContent {
                        copy(
                            isLoading = false,
                            amountError = error.message ?: "Ошибка отправки пожертвования",
                        )
                    }
                }
        }
    }

    private inline fun updateContent(transform: HelpCatUiState.Content.() -> HelpCatUiState.Content) {
        (_state.value as? HelpCatUiState.Content)?.let { _state.value = it.transform() }
    }

    private companion object {
        const val SUCCESS_DISPLAY_MS = 1200L
    }
}
