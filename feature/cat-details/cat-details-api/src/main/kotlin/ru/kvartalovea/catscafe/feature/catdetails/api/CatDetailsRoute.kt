package ru.kvartalovea.catscafe.feature.catdetails.api

import kotlinx.serialization.Serializable

/**
 * Карточка кота. Открывается из каталога. Принимает идентификатор кота
 * (формат держим как `String` — `UUID.toString()` или серверный id).
 */
@Serializable
data class CatDetailsRoute(
    val catId: String,
)
