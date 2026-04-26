package ru.kvartalovea.catscafe.navigation

import kotlinx.serialization.Serializable

/**
 * Временный стартовый маршрут — заглушка, пока не появится `:feature:home:home-impl`.
 * После этого — переехать в `feature-home-api` и удалить отсюда.
 */
@Serializable
data object HomeRoute
