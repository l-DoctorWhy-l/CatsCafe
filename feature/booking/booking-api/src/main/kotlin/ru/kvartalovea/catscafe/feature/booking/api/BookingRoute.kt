package ru.kvartalovea.catscafe.feature.booking.api

import kotlinx.serialization.Serializable

/**
 * Бронирование. Один из табов нижней навигации, а также целевой экран
 * кнопки «Забронировать визит» из карточки кота.
 *
 * `preselectedCatId` — опциональный идентификатор кота, если переход
 * пришёл из карточки конкретного кота.
 */
@Serializable
data class BookingRoute(
    val preselectedCatId: String? = null,
)
