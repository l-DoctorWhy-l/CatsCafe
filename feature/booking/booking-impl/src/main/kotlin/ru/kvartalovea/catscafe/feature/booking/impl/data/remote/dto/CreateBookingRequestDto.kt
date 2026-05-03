package ru.kvartalovea.catscafe.feature.booking.impl.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateBookingRequestDto(
    @SerialName("booking_time") val bookingTime: String,
    @SerialName("guests_count") val guestsCount: Int,
)
