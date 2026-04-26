package ru.kvartalovea.catscafe.core.network.impl

import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

/**
 * Конфигурация сетевого слоя, передаётся из `:app` в [coreNetworkModule].
 *
 */
data class NetworkConfig(
    /** Базовый URL API, должен оканчиваться на `/`. */
    val baseUrl: String,
    /** Включает `HttpLoggingInterceptor` с `Level.BODY`. Обычно `BuildConfig.DEBUG`. */
    val isDebug: Boolean,
    /** Connect timeout. */
    val connectTimeout: Duration = 15.seconds,
    /** Read timeout. */
    val readTimeout: Duration = 30.seconds,
    /** Write timeout. */
    val writeTimeout: Duration = 30.seconds,
)
