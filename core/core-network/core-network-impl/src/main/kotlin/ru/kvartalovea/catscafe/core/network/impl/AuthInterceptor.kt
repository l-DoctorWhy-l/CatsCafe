package ru.kvartalovea.catscafe.core.network.impl

import okhttp3.Interceptor
import okhttp3.Response

/**
 * Добавляет заголовок `Authorization: Bearer <token>` к каждому запросу,
 * если токен доступен. Токен предоставляется через лямбду, которая вычисляется
 * лениво при каждом запросе — это позволяет получить актуальный токен после логина.
 */
internal class AuthInterceptor(
    private val tokenProvider: () -> String?,
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val token = tokenProvider()
        val request = if (token != null) {
            chain.request().newBuilder()
                .addHeader("Authorization", "Bearer $token")
                .build()
        } else {
            chain.request()
        }
        return chain.proceed(request)
    }
}
