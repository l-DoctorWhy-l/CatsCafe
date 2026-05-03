package ru.kvartalovea.catscafe.feature.mybookings.impl.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserBookingDto(
    @SerialName("id") val id: String,
    @SerialName("booking_time") val bookingTime: String,
    @SerialName("guests_count") val guestsCount: Int,
    @SerialName("status") val status: String,
)
