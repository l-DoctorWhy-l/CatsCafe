package ru.kvartalovea.catscafe.feature.booking.impl.presentation.model

import java.time.LocalDate

internal data class CalendarDayUiModel(
    val day: Int,
    val date: LocalDate,
    val isSelected: Boolean,
    val isToday: Boolean,
    val isEnabled: Boolean,
)

internal data class TimeSlotUiModel(
    val time: String,
    val isSelected: Boolean,
    val isAvailable: Boolean,
)
