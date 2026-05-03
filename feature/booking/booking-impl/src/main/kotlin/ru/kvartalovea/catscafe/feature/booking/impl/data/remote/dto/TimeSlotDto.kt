package ru.kvartalovea.catscafe.feature.booking.impl.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TimeSlotDto(
    @SerialName("time") val time: String,
    @SerialName("is_available") val isAvailable: Boolean,
)
