package ru.kvartalovea.catscafe.core.network.impl

import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

/**
 * Собирает корневой [Retrofit] поверх готового [OkHttpClient] и настроенного [Json].
 */
internal fun buildRetrofit(
    okHttpClient: OkHttpClient,
    json: Json,
    config: NetworkConfig,
): Retrofit = Retrofit.Builder()
    .baseUrl(config.baseUrl)
    .client(okHttpClient)
    .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
    .build()
