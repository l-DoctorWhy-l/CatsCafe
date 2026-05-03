package ru.kvartalovea.catscafe.core.network.impl

import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit

/**
 * Koin-модуль `core-network-impl`.
 */
fun coreNetworkModule(config: NetworkConfig, tokenProvider: () -> String?): Module = module {
    single { config }
    single<Json> { NetworkJson }
    single { AuthInterceptor(tokenProvider) }
    single<OkHttpClient> { buildOkHttpClient(get(), get()) }
    single<Retrofit> { buildRetrofit(okHttpClient = get(), json = get(), config = get()) }
}
