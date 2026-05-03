package ru.kvartalovea.catscafe.feature.home.impl.domain.model

internal data class BookingResult(
    val booking: NearestBooking?,
    val isFromCache: Boolean,
)
