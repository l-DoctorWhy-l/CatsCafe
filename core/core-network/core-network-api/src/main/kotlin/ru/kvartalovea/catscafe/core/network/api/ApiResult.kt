package ru.kvartalovea.catscafe.core.network.api

/**
 * Унифицированный результат сетевого вызова.
 *
 * Используется в `data`-слоях фич: репозиторий вызывает `apiCall { service.xxx() }`
 * и работает с типизированным результатом. ViewModel/UseCase **не должны** видеть
 * `ApiResult` напрямую — `feature-X-impl`-data маппит его в свой domain `Result`/sealed.
 */
sealed interface ApiResult<out T> {

    /** Успешный ответ с данными. */
    data class Success<T>(val data: T) : ApiResult<T>

    /** Любая неуспешная ситуация. */
    sealed interface Failure : ApiResult<Nothing> {

        /** HTTP-статус ≥ 400. [body] — сырое тело ответа, если есть. */
        data class Http(
            val code: Int,
            val message: String?,
            val body: String? = null,
        ) : Failure

        /** Нет соединения / сбой сети (IOException, кроме таймаута). */
        data object Network : Failure

        /** Истёк connect/read/write таймаут. */
        data object Timeout : Failure

        /** Не получилось распарсить тело ответа. */
        data class Serialization(val cause: Throwable) : Failure

        /** Прочая необработанная ошибка. */
        data class Unknown(val cause: Throwable) : Failure
    }
}

/** Возвращает данные, если [ApiResult.Success], иначе `null`. */
fun <T> ApiResult<T>.getOrNull(): T? = (this as? ApiResult.Success<T>)?.data

/** Маппинг [ApiResult.Success] значения. Failure пробрасывается без изменений. */
inline fun <T, R> ApiResult<T>.map(transform: (T) -> R): ApiResult<R> = when (this) {
    is ApiResult.Success -> ApiResult.Success(transform(data))
    is ApiResult.Failure -> this
}
