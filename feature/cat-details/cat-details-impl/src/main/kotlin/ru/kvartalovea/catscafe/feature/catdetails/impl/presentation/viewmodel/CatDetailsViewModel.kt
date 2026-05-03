package ru.kvartalovea.catscafe.feature.catdetails.impl.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.kvartalovea.catscafe.core.navigation.api.AppNavigator
import ru.kvartalovea.catscafe.feature.booking.api.BookingRoute
import ru.kvartalovea.catscafe.feature.helpcat.api.HelpCatRoute
import ru.kvartalovea.catscafe.feature.catdetails.impl.domain.usecase.GetCatDetailsUseCase
import ru.kvartalovea.catscafe.feature.catdetails.impl.presentation.model.CatDetailsUiEvent
import ru.kvartalovea.catscafe.feature.catdetails.impl.presentation.model.CatDetailsUiState
import ru.kvartalovea.catscafe.feature.catdetails.impl.presentation.model.mapper.toUiModel

internal class CatDetailsViewModel(
    private val catId: String,
    private val getCatDetailsUseCase: GetCatDetailsUseCase,
    private val navigator: AppNavigator,
) : ViewModel() {

    private val _state = MutableStateFlow<CatDetailsUiState>(CatDetailsUiState.Loading)
    val state: StateFlow<CatDetailsUiState> = _state.asStateFlow()

    init {
        loadCat()
    }

    fun onEvent(event: CatDetailsUiEvent) {
        when (event) {
            CatDetailsUiEvent.OnBackClick -> navigator.popBackStack()
            CatDetailsUiEvent.OnHelpCatClick -> {
                val content = _state.value as? CatDetailsUiState.Content ?: return
                navigator.navigate(HelpCatRoute(catId = catId, catName = content.cat.name))
            }
            CatDetailsUiEvent.OnBookVisitClick -> navigator.navigate(BookingRoute(preselectedCatId = catId))
        }
    }

    private fun loadCat() {
        viewModelScope.launch {
            getCatDetailsUseCase(catId)
                .onSuccess { cat ->
                    _state.value = if (cat != null) {
                        CatDetailsUiState.Content(cat.toUiModel())
                    } else {
                        CatDetailsUiState.NotFound
                    }
                }
                .onFailure {
                    _state.value = CatDetailsUiState.NotFound
                }
        }
    }
}
