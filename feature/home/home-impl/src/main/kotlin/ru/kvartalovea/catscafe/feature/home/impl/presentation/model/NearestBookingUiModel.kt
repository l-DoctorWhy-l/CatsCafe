package ru.kvartalovea.catscafe.feature.home.impl.presentation.model

internal data class NearestBookingUiModel(
    val date: String,
    val time: String,
    val guestsCount: Int,
    val qrCodeUrl: String?,
)
