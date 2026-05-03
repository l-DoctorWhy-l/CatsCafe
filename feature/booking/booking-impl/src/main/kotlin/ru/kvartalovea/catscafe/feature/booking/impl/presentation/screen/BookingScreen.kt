package ru.kvartalovea.catscafe.feature.booking.impl.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBackIos
import androidx.compose.material.icons.automirrored.outlined.ArrowForwardIos
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf
import ru.kvartalovea.catscafe.feature.booking.impl.presentation.model.BookingUiEvent
import ru.kvartalovea.catscafe.feature.booking.impl.presentation.model.BookingUiState
import ru.kvartalovea.catscafe.feature.booking.impl.presentation.model.CalendarDayUiModel
import ru.kvartalovea.catscafe.feature.booking.impl.presentation.model.TimeSlotUiModel
import ru.kvartalovea.catscafe.feature.booking.impl.presentation.viewmodel.BookingViewModel

private val WEEK_DAYS = listOf("Пн", "Вт", "Ср", "Чт", "Пт", "Сб", "Вс")

@Composable
internal fun BookingScreen(
    preselectedCatId: String?,
    viewModel: BookingViewModel = koinViewModel(parameters = { parametersOf(preselectedCatId) }),
) {
    val state by viewModel.state.collectAsState()
    BookingContent(state = state, onEvent = viewModel::onEvent)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun BookingContent(
    state: BookingUiState,
    onEvent: (BookingUiEvent) -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Бронирование визита",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                ),
            )
        },
        bottomBar = {
            if (state is BookingUiState.Content) {
                ConfirmButton(
                    enabled = state.isConfirmEnabled,
                    onClick = { onEvent(BookingUiEvent.OnConfirmClick) },
                )
            }
        },
    ) { paddingValues ->
        when (state) {
            is BookingUiState.Content -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .verticalScroll(rememberScrollState())
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    verticalArrangement = Arrangement.spacedBy(20.dp),
                ) {
                    CalendarCard(
                        monthTitle = state.monthTitle,
                        isPrevEnabled = state.isPrevMonthEnabled,
                        calendarDays = state.calendarDays,
                        onPrevClick = { onEvent(BookingUiEvent.OnPrevMonthClick) },
                        onNextClick = { onEvent(BookingUiEvent.OnNextMonthClick) },
                        onDayClick = { day -> onEvent(BookingUiEvent.OnDateSelected(day.date)) },
                    )

                    if (state.timeSlots.isNotEmpty()) {
                        TimeSelectionSection(
                            slots = state.timeSlots,
                            onSlotClick = { slot ->
                                if (slot.isAvailable) onEvent(BookingUiEvent.OnTimeSelected(slot.time))
                            },
                        )
                    }

                    GuestsSection(
                        counts = state.guestCounts,
                        selected = state.selectedGuestCount,
                        onSelect = { onEvent(BookingUiEvent.OnGuestCountSelected(it)) },
                    )

                    Spacer(modifier = Modifier.height(8.dp))
                }
            }

            BookingUiState.Success -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    contentAlignment = Alignment.Center,
                ) {
                    SuccessState()
                }
            }
        }
    }
}

// ──────────────────────────── Calendar ────────────────────────────

@Composable
private fun CalendarCard(
    monthTitle: String,
    isPrevEnabled: Boolean,
    calendarDays: List<CalendarDayUiModel?>,
    onPrevClick: () -> Unit,
    onNextClick: () -> Unit,
    onDayClick: (CalendarDayUiModel) -> Unit,
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
    ) {
        Column(modifier = Modifier.padding(horizontal = 8.dp, vertical = 12.dp)) {
            // Month navigation header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                IconButton(
                    onClick = onPrevClick,
                    enabled = isPrevEnabled,
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Outlined.ArrowBackIos,
                        contentDescription = "Предыдущий месяц",
                        modifier = Modifier.size(16.dp),
                        tint = if (isPrevEnabled) {
                            MaterialTheme.colorScheme.onSurface
                        } else {
                            MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f)
                        },
                    )
                }
                Text(
                    text = monthTitle,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Medium,
                )
                IconButton(onClick = onNextClick) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Outlined.ArrowForwardIos,
                        contentDescription = "Следующий месяц",
                        modifier = Modifier.size(16.dp),
                    )
                }
            }

            Spacer(modifier = Modifier.height(4.dp))

            // Week day labels
            Row(modifier = Modifier.fillMaxWidth()) {
                WEEK_DAYS.forEach { label ->
                    Text(
                        text = label,
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontWeight = FontWeight.Medium,
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Day grid: split flat list into rows of 7
            calendarDays.chunked(7).forEach { week ->
                Row(modifier = Modifier.fillMaxWidth()) {
                    // Pad last row to always have 7 cells
                    val paddedWeek = week + List(7 - week.size) { null }
                    paddedWeek.forEach { day ->
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .padding(vertical = 2.dp),
                            contentAlignment = Alignment.Center,
                        ) {
                            if (day != null) {
                                DayCell(day = day, onClick = { onDayClick(day) })
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun DayCell(
    day: CalendarDayUiModel,
    onClick: () -> Unit,
) {
    val isSelected = day.isSelected
    val isEnabled = day.isEnabled

    Box(
        modifier = Modifier
            .size(36.dp)
            .clip(CircleShape)
            .background(
                when {
                    isSelected -> MaterialTheme.colorScheme.primary
                    else -> androidx.compose.ui.graphics.Color.Transparent
                },
            )
            .then(
                if (isEnabled) Modifier.clickable(onClick = onClick)
                else Modifier
            )
            .alpha(if (isEnabled) 1f else 0.35f),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = day.day.toString(),
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = if (isSelected || day.isToday) FontWeight.Bold else FontWeight.Normal,
            color = when {
                isSelected -> MaterialTheme.colorScheme.onPrimary
                day.isToday -> MaterialTheme.colorScheme.primary
                else -> MaterialTheme.colorScheme.onSurface
            },
        )
    }
}

// ──────────────────────────── Time slots ────────────────────────────

@Composable
private fun TimeSelectionSection(
    slots: List<TimeSlotUiModel>,
    onSlotClick: (TimeSlotUiModel) -> Unit,
) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(
            text = "Выберите время",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold,
        )
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        ) {
            Column(
                modifier = Modifier.padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                slots.chunked(3).forEach { row ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        row.forEach { slot ->
                            TimeSlotChip(
                                slot = slot,
                                modifier = Modifier.weight(1f),
                                onClick = { onSlotClick(slot) },
                            )
                        }
                        // Fill remaining cells in last row
                        repeat(3 - row.size) {
                            Spacer(modifier = Modifier.weight(1f))
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun TimeSlotChip(
    slot: TimeSlotUiModel,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    val isSelected = slot.isSelected
    val isAvailable = slot.isAvailable

    Surface(
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .then(if (isAvailable) Modifier.clickable(onClick = onClick) else Modifier),
        shape = RoundedCornerShape(10.dp),
        color = when {
            isSelected -> MaterialTheme.colorScheme.primary
            else -> MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f)
        },
    ) {
        Text(
            text = slot.time,
            modifier = Modifier
                .padding(vertical = 10.dp)
                .alpha(if (isAvailable) 1f else 0.4f),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal,
            color = if (isSelected) {
                MaterialTheme.colorScheme.onPrimary
            } else {
                MaterialTheme.colorScheme.onSurface
            },
        )
    }
}

// ──────────────────────────── Guests ────────────────────────────

@Composable
private fun GuestsSection(
    counts: List<Int>,
    selected: Int,
    onSelect: (Int) -> Unit,
) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(
            text = "Количество гостей",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold,
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            counts.forEach { count ->
                GuestCountChip(
                    count = count,
                    isSelected = count == selected,
                    onClick = { onSelect(count) },
                )
            }
        }
    }
}

@Composable
private fun GuestCountChip(
    count: Int,
    isSelected: Boolean,
    onClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .size(48.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(
                if (isSelected) MaterialTheme.colorScheme.primary
                else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f),
            )
            .then(
                if (!isSelected) {
                    Modifier.border(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.outline.copy(alpha = 0.3f),
                        shape = RoundedCornerShape(12.dp),
                    )
                } else Modifier
            )
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = count.toString(),
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Medium,
            color = if (isSelected) MaterialTheme.colorScheme.onPrimary
            else MaterialTheme.colorScheme.onSurface,
        )
    }
}

// ──────────────────────────── Bottom button ────────────────────────────

@Composable
private fun ConfirmButton(enabled: Boolean, onClick: () -> Unit) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.background,
        shadowElevation = 8.dp,
    ) {
        Button(
            onClick = onClick,
            enabled = enabled,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                disabledContainerColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f),
            ),
        ) {
            Text(
                text = "Подтвердить бронь",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Medium,
            )
        }
    }
}

// ──────────────────────────── Success ────────────────────────────

@Composable
private fun SuccessState() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.padding(32.dp),
    ) {
        Icon(
            imageVector = Icons.Outlined.CheckCircle,
            contentDescription = null,
            modifier = Modifier.size(72.dp),
            tint = MaterialTheme.colorScheme.primary,
        )
        Text(
            text = "Бронь подтверждена!",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
        )
        Text(
            text = "Детали брони появятся в разделе «Мои бронирования»",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center,
        )
    }
}
