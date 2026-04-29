package ru.kvartalovea.catscafe.feature.mybookings.impl.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.kvartalovea.catscafe.core.navigation.api.AppNavigator
import ru.kvartalovea.catscafe.feature.mybookings.impl.domain.usecase.GetMyBookingsUseCase
import ru.kvartalovea.catscafe.feature.mybookings.impl.presentation.model.MyBookingsUiEvent
import ru.kvartalovea.catscafe.feature.mybookings.impl.presentation.model.MyBookingsUiState
import ru.kvartalovea.catscafe.feature.mybookings.impl.presentation.model.mapper.toUiModels

internal class MyBookingsViewModel(
    private val getMyBookingsUseCase: GetMyBookingsUseCase,
    private val navigator: AppNavigator,
) : ViewModel() {

    private val _state = MutableStateFlow<MyBookingsUiState>(MyBookingsUiState.Loading)
    val state: StateFlow<MyBookingsUiState> = _state.asStateFlow()

    init {
        load()
    }

    fun onEvent(event: MyBookingsUiEvent) {
        when (event) {
            MyBookingsUiEvent.OnBackClick -> navigator.popBackStack()
            MyBookingsUiEvent.Refresh -> load()
        }
    }

    private fun load() {
        viewModelScope.launch {
            _state.value = MyBookingsUiState.Loading
            getMyBookingsUseCase()
                .onSuccess { bookings ->
                    _state.value = MyBookingsUiState.Content(bookings.toUiModels())
                }
                .onFailure { error ->
                    _state.value = MyBookingsUiState.Error(
                        error.message ?: "Неизвестная ошибка",
                    )
                }
        }
    }
}
