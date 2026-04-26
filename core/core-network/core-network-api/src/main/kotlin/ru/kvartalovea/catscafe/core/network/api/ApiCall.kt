package ru.kvartalovea.catscafe.core.network.api

import kotlin.coroutines.cancellation.CancellationException
import kotlinx.serialization.SerializationException
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

/**
 * Обёртка над `suspend` сетевым вызовом, превращающая исключения Retrofit/OkHttp/kotlinx-serialization
 * в типизированный [ApiResult].
 *
 * Использование в `feature-X-impl`-data:
 *
 * ```
 * class CatRepositoryImpl(private val api: CatApi) {
 *     suspend fun loadCats(): ApiResult<List<Cat>> = apiCall {
 *         api.getCats().map(CatDto::toDomain)
 *     }
 * }
 * ```
 *
 * `CancellationException` пробрасывается без оборачивания — корутины должны
 * корректно отменяться по контракту structured concurrency.
 */
suspend inline fun <T> apiCall(crossinline block: suspend () -> T): ApiResult<T> = try {
    ApiResult.Success(block())
} catch (cancellation: CancellationException) {
    throw cancellation
} catch (e: HttpException) {
    ApiResult.Failure.Http(
        code = e.code(),
        message = e.message(),
        body = runCatching { e.response()?.errorBody()?.string() }.getOrNull(),
    )
} catch (e: SocketTimeoutException) {
    ApiResult.Failure.Timeout
} catch (e: IOException) {
    ApiResult.Failure.Network
} catch (e: SerializationException) {
    ApiResult.Failure.Serialization(e)
} catch (e: Throwable) {
    ApiResult.Failure.Unknown(e)
}
