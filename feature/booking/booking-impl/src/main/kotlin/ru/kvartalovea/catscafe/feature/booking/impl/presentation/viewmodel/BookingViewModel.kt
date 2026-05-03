package ru.kvartalovea.catscafe.feature.booking.impl.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.kvartalovea.catscafe.core.navigation.api.AppNavigator
import ru.kvartalovea.catscafe.feature.booking.impl.domain.model.TimeSlot
import ru.kvartalovea.catscafe.feature.booking.impl.domain.usecase.CreateBookingUseCase
import ru.kvartalovea.catscafe.feature.booking.impl.domain.usecase.GetAvailableSlotsUseCase
import ru.kvartalovea.catscafe.feature.booking.impl.presentation.model.BookingUiEvent
import ru.kvartalovea.catscafe.feature.booking.impl.presentation.model.BookingUiState
import ru.kvartalovea.catscafe.feature.booking.impl.presentation.model.mapper.buildCalendarDays
import ru.kvartalovea.catscafe.feature.booking.impl.presentation.model.mapper.toDisplayTitle
import ru.kvartalovea.catscafe.feature.booking.impl.presentation.model.mapper.toUiModels
import java.time.LocalDate
import java.time.YearMonth

internal class BookingViewModel(
    private val preselectedCatId: String?,
    private val getAvailableSlotsUseCase: GetAvailableSlotsUseCase,
    private val createBookingUseCase: CreateBookingUseCase,
    private val navigator: AppNavigator,
) : ViewModel() {

    private var currentYearMonth = YearMonth.now()
    private var selectedDate: LocalDate? = null
    private var allSlots: List<TimeSlot> = emptyList()
    private var selectedTime: String? = null
    private var selectedGuestCount: Int = 1

    private val _state = MutableStateFlow<BookingUiState>(buildContentState())
    val state: StateFlow<BookingUiState> = _state.asStateFlow()

    fun onEvent(event: BookingUiEvent) {
        when (event) {
            BookingUiEvent.OnPrevMonthClick -> {
                val prevMonth = currentYearMonth.minusMonths(1)
                if (!prevMonth.isBefore(YearMonth.now())) {
                    currentYearMonth = prevMonth
                    if (selectedDate != null && YearMonth.from(selectedDate) != currentYearMonth) {
                        selectedDate = null
                        allSlots = emptyList()
                        selectedTime = null
                    }
                    updateContent()
                }
            }

            BookingUiEvent.OnNextMonthClick -> {
                currentYearMonth = currentYearMonth.plusMonths(1)
                if (selectedDate != null && YearMonth.from(selectedDate) != currentYearMonth) {
                    selectedDate = null
                    allSlots = emptyList()
                    selectedTime = null
                }
                updateContent()
            }

            is BookingUiEvent.OnDateSelected -> {
                selectedDate = event.date
                selectedTime = null
                allSlots = emptyList()
                updateContent()
                loadSlots(event.date)
            }

            is BookingUiEvent.OnTimeSelected -> {
                if (allSlots.find { it.time == event.time }?.isAvailable == true) {
                    selectedTime = event.time
                    updateContent()
                }
            }

            is BookingUiEvent.OnGuestCountSelected -> {
                selectedGuestCount = event.count
                updateContent()
            }

            BookingUiEvent.OnConfirmClick -> confirmBooking()
        }
    }

    private fun loadSlots(date: LocalDate) {
        viewModelScope.launch {
            getAvailableSlotsUseCase(date.toString())
                .onSuccess { slots ->
                    allSlots = slots
                    updateContent()
                }
        }
    }

    private fun confirmBooking() {
        val date = selectedDate ?: return
        val time = selectedTime ?: return
        viewModelScope.launch {
            createBookingUseCase(date.toString(), time, selectedGuestCount)
                .onSuccess {
                    _state.value = BookingUiState.Success
                }
        }
    }

    private fun updateContent() {
        _state.value = buildContentState()
    }

    private fun buildContentState() = BookingUiState.Content(
        monthTitle = currentYearMonth.toDisplayTitle(),
        isPrevMonthEnabled = currentYearMonth > YearMonth.now(),
        calendarDays = buildCalendarDays(currentYearMonth, selectedDate),
        timeSlots = allSlots.toUiModels(selectedTime),
        guestCounts = (1..8).toList(),
        selectedGuestCount = selectedGuestCount,
        isConfirmEnabled = selectedDate != null && selectedTime != null,
    )
}
