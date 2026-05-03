package ru.kvartalovea.catscafe.feature.booking.impl.domain.model

internal data class TimeSlot(
    val time: String,
    val isAvailable: Boolean,
)
