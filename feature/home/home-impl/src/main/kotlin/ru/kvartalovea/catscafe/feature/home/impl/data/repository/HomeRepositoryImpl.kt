package ru.kvartalovea.catscafe.feature.home.impl.data.repository

import ru.kvartalovea.catscafe.core.network.api.ApiResult
import ru.kvartalovea.catscafe.core.network.api.apiCall
import ru.kvartalovea.catscafe.feature.home.impl.data.remote.api.HomeApiService
import ru.kvartalovea.catscafe.feature.home.impl.data.remote.dto.NearestBookingDto
import ru.kvartalovea.catscafe.feature.home.impl.data.remote.dto.NewsItemDto
import ru.kvartalovea.catscafe.feature.home.impl.domain.model.NearestBooking
import ru.kvartalovea.catscafe.feature.home.impl.domain.model.NewsItem
import ru.kvartalovea.catscafe.feature.home.impl.domain.repository.HomeRepository

internal class HomeRepositoryImpl(
    private val api: HomeApiService,
) : HomeRepository {

    override suspend fun getNews(): Result<List<NewsItem>> =
        apiCall { api.getNews().map { it.toDomain() } }.toResult()

    override suspend fun getNearestBooking(): Result<NearestBooking?> =
        when (val result = apiCall { api.getNearestBooking() }) {
            is ApiResult.Success -> Result.success(result.data.toDomain())
            is ApiResult.Failure.Http -> if (result.code == 404) {
                Result.success(null)
            } else {
                Result.failure(Exception("HTTP ${result.code}: ${result.message}"))
            }
            is ApiResult.Failure.Network -> Result.failure(Exception("Нет подключения к сети"))
            is ApiResult.Failure.Timeout -> Result.failure(Exception("Превышено время ожидания"))
            is ApiResult.Failure.Serialization -> Result.failure(result.cause)
            is ApiResult.Failure.Unknown -> Result.failure(result.cause)
        }

    private fun NewsItemDto.toDomain() = NewsItem(
        id = id,
        title = title,
        description = description,
        imageUrl = imageUrl,
        publishedAt = publishedAt,
    )

    private fun NearestBookingDto.toDomain() = NearestBooking(
        date = date,
        time = time,
        guestsCount = guestsCount,
        qrCodeUrl = qrCodeUrl,
    )

    private fun <T> ApiResult<T>.toResult(): Result<T> = when (this) {
        is ApiResult.Success -> Result.success(data)
        is ApiResult.Failure.Http -> Result.failure(Exception("HTTP $code: $message"))
        is ApiResult.Failure.Network -> Result.failure(Exception("Нет подключения к сети"))
        is ApiResult.Failure.Timeout -> Result.failure(Exception("Превышено время ожидания"))
        is ApiResult.Failure.Serialization -> Result.failure(cause)
        is ApiResult.Failure.Unknown -> Result.failure(cause)
    }
}
