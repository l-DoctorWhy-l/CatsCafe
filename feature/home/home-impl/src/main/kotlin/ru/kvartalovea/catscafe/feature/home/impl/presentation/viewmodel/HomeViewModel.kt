package ru.kvartalovea.catscafe.feature.home.impl.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.kvartalovea.catscafe.feature.home.impl.domain.usecase.GetNearestBookingUseCase
import ru.kvartalovea.catscafe.feature.home.impl.domain.usecase.GetNewsUseCase
import ru.kvartalovea.catscafe.feature.home.impl.presentation.model.HomeUiEvent
import ru.kvartalovea.catscafe.feature.home.impl.presentation.model.HomeUiState
import ru.kvartalovea.catscafe.feature.home.impl.presentation.model.mapper.toUiModel
import ru.kvartalovea.catscafe.feature.home.impl.presentation.model.mapper.toUiModels

internal class HomeViewModel(
    private val getNewsUseCase: GetNewsUseCase,
    private val getNearestBookingUseCase: GetNearestBookingUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val state: StateFlow<HomeUiState> = _state.asStateFlow()

    init {
        loadData()
    }

    fun onEvent(event: HomeUiEvent) {
        when (event) {
            HomeUiEvent.Refresh -> loadData()
            is HomeUiEvent.OnNewsClick -> Unit
            HomeUiEvent.OnBookingClick -> Unit
            HomeUiEvent.OnNotificationsClick -> Unit
        }
    }

    private fun loadData() {
        viewModelScope.launch {
            _state.value = HomeUiState.Loading

            val newsDeferred = async { getNewsUseCase() }
            val bookingDeferred = async { getNearestBookingUseCase() }

            val newsResult = newsDeferred.await()
            val bookingResult = bookingDeferred.await()

            _state.value = when {
                newsResult.isFailure -> HomeUiState.Error(
                    newsResult.exceptionOrNull()?.message ?: "Неизвестная ошибка",
                )
                bookingResult.isFailure -> HomeUiState.Error(
                    bookingResult.exceptionOrNull()?.message ?: "Неизвестная ошибка",
                )
                else -> HomeUiState.Content(
                    nearestBooking = bookingResult.getOrNull()?.toUiModel(),
                    newsList = newsResult.getOrNull()?.toUiModels() ?: emptyList(),
                )
            }
        }
    }
}
