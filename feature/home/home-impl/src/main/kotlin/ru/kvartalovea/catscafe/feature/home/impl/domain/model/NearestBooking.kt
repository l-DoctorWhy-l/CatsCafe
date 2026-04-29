package ru.kvartalovea.catscafe.feature.home.impl.domain.model

data class NearestBooking(
    val date: String,
    val time: String,
    val guestsCount: Int,
    val qrCodeUrl: String?,
)
