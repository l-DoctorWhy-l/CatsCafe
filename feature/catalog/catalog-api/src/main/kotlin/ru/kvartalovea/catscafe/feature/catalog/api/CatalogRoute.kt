package ru.kvartalovea.catscafe.feature.catalog.api

import kotlinx.serialization.Serializable

/**
 * Каталог котов. Один из табов нижней навигации.
 * Открывает `CatDetailsRoute(catId)` по тапу на карточку.
 */
@Serializable
data object CatalogRoute
