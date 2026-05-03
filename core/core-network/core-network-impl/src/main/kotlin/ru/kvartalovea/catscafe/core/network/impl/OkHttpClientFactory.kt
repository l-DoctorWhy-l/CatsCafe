package ru.kvartalovea.catscafe.core.network.impl

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import kotlin.time.toJavaDuration

/**
 * Собирает [OkHttpClient] под нашу конфигурацию: таймауты + `HttpLoggingInterceptor`
 * только в debug.
 */
internal fun buildOkHttpClient(config: NetworkConfig, authInterceptor: AuthInterceptor): OkHttpClient {
    val builder = OkHttpClient.Builder()
        .connectTimeout(config.connectTimeout.toJavaDuration())
        .readTimeout(config.readTimeout.toJavaDuration())
        .writeTimeout(config.writeTimeout.toJavaDuration())
        .addInterceptor(authInterceptor)

    if (config.isDebug) {
        val logging = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
        builder.addInterceptor(logging)
    }

    return builder.build()
}
