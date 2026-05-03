package ru.kvartalovea.catscafe.feature.booking.impl.presentation.model.mapper

import ru.kvartalovea.catscafe.feature.booking.impl.domain.model.TimeSlot
import ru.kvartalovea.catscafe.feature.booking.impl.presentation.model.CalendarDayUiModel
import ru.kvartalovea.catscafe.feature.booking.impl.presentation.model.TimeSlotUiModel
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.Locale

internal fun TimeSlot.toUiModel(selectedTime: String?) = TimeSlotUiModel(
    time = time,
    isSelected = time == selectedTime,
    isAvailable = isAvailable,
)

internal fun List<TimeSlot>.toUiModels(selectedTime: String?) =
    map { it.toUiModel(selectedTime) }

internal fun buildCalendarDays(
    yearMonth: YearMonth,
    selectedDate: LocalDate?,
): List<CalendarDayUiModel?> {
    val today = LocalDate.now()
    val firstDay = yearMonth.atDay(1)
    val daysInMonth = yearMonth.lengthOfMonth()
    // DayOfWeek: MONDAY=1 … SUNDAY=7; offset = how many nulls before first day
    val startOffset = firstDay.dayOfWeek.value - 1

    val result = mutableListOf<CalendarDayUiModel?>()
    repeat(startOffset) { result.add(null) }
    for (day in 1..daysInMonth) {
        val date = yearMonth.atDay(day)
        result.add(
            CalendarDayUiModel(
                day = day,
                date = date,
                isSelected = date == selectedDate,
                isToday = date == today,
                isEnabled = !date.isBefore(today),
            ),
        )
    }
    return result
}

internal fun YearMonth.toDisplayTitle(): String {
    val monthName = month
        .getDisplayName(TextStyle.FULL_STANDALONE, Locale("ru"))
        .replaceFirstChar { it.uppercase() }
    return "$monthName $year г."
}
