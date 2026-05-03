package ru.kvartalovea.catscafe.feature.donations.impl.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.kvartalovea.catscafe.core.navigation.api.AppNavigator
import ru.kvartalovea.catscafe.common.utils.UiText
import ru.kvartalovea.catscafe.feature.donations.impl.R
import ru.kvartalovea.catscafe.feature.donations.impl.domain.usecase.GetDonationsUseCase
import ru.kvartalovea.catscafe.feature.donations.impl.presentation.model.DonationsUiEvent
import ru.kvartalovea.catscafe.feature.donations.impl.presentation.model.DonationsUiState
import ru.kvartalovea.catscafe.feature.donations.impl.presentation.model.mapper.toUiModels

internal class DonationsViewModel(
    private val getDonationsUseCase: GetDonationsUseCase,
    private val navigator: AppNavigator,
) : ViewModel() {

    private val _state = MutableStateFlow<DonationsUiState>(DonationsUiState.Loading)
    val state: StateFlow<DonationsUiState> = _state.asStateFlow()

    init {
        load()
    }

    fun onEvent(event: DonationsUiEvent) {
        when (event) {
            DonationsUiEvent.OnBackClick -> navigator.popBackStack()
            DonationsUiEvent.Refresh -> load()
        }
    }

    private fun load() {
        viewModelScope.launch {
            _state.value = DonationsUiState.Loading
            getDonationsUseCase()
                .onSuccess { donations ->
                    _state.value = DonationsUiState.Content(donations.toUiModels())
                }
                .onFailure { error ->
                    _state.value = DonationsUiState.Error(
                        error.message?.let { UiText.DynamicString(it) }
                            ?: UiText.StringRes(R.string.error_unknown),
                    )
                }
        }
    }
}
