package ru.kvartalovea.catscafe.feature.catalog.impl.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.kvartalovea.catscafe.core.navigation.api.AppNavigator
import ru.kvartalovea.catscafe.common.utils.UiText
import ru.kvartalovea.catscafe.feature.catalog.impl.R
import ru.kvartalovea.catscafe.feature.catalog.impl.domain.model.Cat
import ru.kvartalovea.catscafe.feature.catalog.impl.domain.usecase.GetCatsUseCase
import ru.kvartalovea.catscafe.feature.catalog.impl.presentation.model.CatFilter
import ru.kvartalovea.catscafe.feature.catalog.impl.presentation.model.CatalogUiEvent
import ru.kvartalovea.catscafe.feature.catalog.impl.presentation.model.CatalogUiState
import ru.kvartalovea.catscafe.feature.catalog.impl.presentation.model.mapper.toUiModels
import ru.kvartalovea.catscafe.feature.catdetails.api.CatDetailsRoute

internal class CatalogViewModel(
    private val getCatsUseCase: GetCatsUseCase,
    private val navigator: AppNavigator,
) : ViewModel() {

    private val _state = MutableStateFlow<CatalogUiState>(CatalogUiState.Loading)
    val state: StateFlow<CatalogUiState> = _state.asStateFlow()

    private var allCats: List<Cat> = emptyList()
    private var currentQuery: String = ""
    private var currentFilter: CatFilter = CatFilter.All

    init {
        loadCats()
    }

    fun onEvent(event: CatalogUiEvent) {
        when (event) {
            CatalogUiEvent.Refresh -> loadCats()
            is CatalogUiEvent.OnSearchQueryChanged -> {
                currentQuery = event.query
                updateContent()
            }
            is CatalogUiEvent.OnFilterSelected -> {
                currentFilter = event.filter
                updateContent()
            }
            is CatalogUiEvent.OnCatClicked -> {
                navigator.navigate(CatDetailsRoute(event.catId))
            }
        }
    }

    private fun loadCats() {
        viewModelScope.launch {
            _state.value = CatalogUiState.Loading
            val result = getCatsUseCase()
            if (result.isSuccess) {
                allCats = result.getOrDefault(emptyList())
                updateContent()
            } else {
                _state.value = CatalogUiState.Error(
                    result.exceptionOrNull()?.message?.let { UiText.DynamicString(it) }
                        ?: UiText.StringRes(R.string.error_unknown),
                )
            }
        }
    }

    private fun updateContent() {
        _state.value = CatalogUiState.Content(
            filteredCats = applyFilters(allCats, currentQuery, currentFilter).toUiModels(),
            activeFilter = currentFilter,
            searchQuery = currentQuery,
        )
    }

    private fun applyFilters(cats: List<Cat>, query: String, filter: CatFilter): List<Cat> =
        cats
            .filter { cat ->
                when (filter) {
                    CatFilter.All -> true
                    CatFilter.LookingForHome -> cat.isLookingForHome
                    CatFilter.New -> cat.isNew
                }
            }
            .filter { cat ->
                query.isBlank() || cat.name.contains(query, ignoreCase = true)
            }
}
