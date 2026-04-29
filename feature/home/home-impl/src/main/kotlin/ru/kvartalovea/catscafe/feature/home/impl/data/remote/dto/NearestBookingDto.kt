package ru.kvartalovea.catscafe.feature.home.impl.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NearestBookingDto(
    @SerialName("date") val date: String,
    @SerialName("time") val time: String,
    @SerialName("guests_count") val guestsCount: Int,
    @SerialName("qr_code_url") val qrCodeUrl: String? = null,
)
