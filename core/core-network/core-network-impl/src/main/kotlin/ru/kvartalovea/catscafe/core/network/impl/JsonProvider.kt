package ru.kvartalovea.catscafe.core.network.impl

import kotlinx.serialization.json.Json

/**
 * Единая настройка `kotlinx.serialization.Json` для всех Retrofit-сервисов приложения.
 *
 * - `ignoreUnknownKeys = true` — стабильность к новым полям бэка без падений.
 * - `coerceInputValues = true` — `null` для не-nullable примитивов превращается в default.
 * - `explicitNulls = false` — не сериализуем явные `null` для опциональных полей в исходящих запросах.
 */
internal val NetworkJson: Json = Json {
    ignoreUnknownKeys = true
    coerceInputValues = true
    explicitNulls = false
    encodeDefaults = false
    isLenient = true
}
