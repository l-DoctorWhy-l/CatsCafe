package ru.kvartalovea.catscafe.feature.home.impl.data.repository

import ru.kvartalovea.catscafe.core.database.api.dao.NearestBookingDao
import ru.kvartalovea.catscafe.core.database.api.dao.NewsDao
import ru.kvartalovea.catscafe.core.database.api.entity.NearestBookingEntity
import ru.kvartalovea.catscafe.core.database.api.entity.NewsItemEntity
import ru.kvartalovea.catscafe.core.network.api.ApiResult
import ru.kvartalovea.catscafe.core.network.api.apiCall
import ru.kvartalovea.catscafe.feature.home.impl.data.remote.api.HomeApiService
import ru.kvartalovea.catscafe.feature.home.impl.data.remote.dto.NearestBookingDto
import ru.kvartalovea.catscafe.feature.home.impl.data.remote.dto.NewsItemDto
import ru.kvartalovea.catscafe.feature.home.impl.domain.model.BookingResult
import ru.kvartalovea.catscafe.feature.home.impl.domain.model.NearestBooking
import ru.kvartalovea.catscafe.feature.home.impl.domain.model.NewsItem
import ru.kvartalovea.catscafe.feature.home.impl.domain.model.NewsResult
import ru.kvartalovea.catscafe.feature.home.impl.domain.repository.HomeRepository
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

internal class HomeRepositoryImpl(
    private val api: HomeApiService,
    private val newsDao: NewsDao,
    private val nearestBookingDao: NearestBookingDao,
) : HomeRepository {

    override suspend fun getNews(): Result<NewsResult> {
        return when (val result = apiCall { api.getNews() }) {
            is ApiResult.Success -> {
                val items = result.data.map { it.toDomain() }
                newsDao.clearAll()
                newsDao.insertAll(items.map { it.toEntity() })
                Result.success(NewsResult(items = items, isFromCache = false))
            }
            is ApiResult.Failure.Network,
            is ApiResult.Failure.Timeout -> {
                val cached = newsDao.getAll()
                if (cached.isNotEmpty()) {
                    Result.success(NewsResult(items = cached.map { it.toDomain() }, isFromCache = true))
                } else {
                    Result.failure(Exception("Нет подключения к сети"))
                }
            }
            is ApiResult.Failure.Http -> Result.failure(
                Exception("HTTP ${(result as ApiResult.Failure.Http).code}: ${result.message}"),
            )
            is ApiResult.Failure.Serialization -> Result.failure((result as ApiResult.Failure.Serialization).cause)
            is ApiResult.Failure.Unknown -> Result.failure((result as ApiResult.Failure.Unknown).cause)
        }
    }

    override suspend fun getNearestBooking(): Result<BookingResult> {
        return when (val result = apiCall { api.getNearestBooking() }) {
            is ApiResult.Success -> {
                val booking = result.data.toDomain()
                nearestBookingDao.save(booking.toEntity())
                Result.success(BookingResult(booking = booking, isFromCache = false))
            }
            is ApiResult.Failure.Http -> if (result.code == 404) {
                nearestBookingDao.clear()
                Result.success(BookingResult(booking = null, isFromCache = false))
            } else {
                Result.failure(Exception("HTTP ${result.code}: ${result.message}"))
            }
            is ApiResult.Failure.Network,
            is ApiResult.Failure.Timeout -> {
                val cached = nearestBookingDao.get()
                Result.success(BookingResult(booking = cached?.toDomain(), isFromCache = true))
            }
            is ApiResult.Failure.Serialization -> Result.failure(result.cause)
            is ApiResult.Failure.Unknown -> Result.failure(result.cause)
        }
    }

    // ── Mappers ──────────────────────────────────────────────────────────────

    private fun NewsItemDto.toDomain() = NewsItem(
        id = id,
        title = title,
        description = description,
        imageUrl = imageUrl,
        publishedAt = publishedAt,
    )

    private fun NewsItem.toEntity() = NewsItemEntity(
        id = id,
        title = title,
        description = description,
        imageUrl = imageUrl,
        publishedAt = publishedAt,
    )

    private fun NewsItemEntity.toDomain() = NewsItem(
        id = id,
        title = title,
        description = description,
        imageUrl = imageUrl,
        publishedAt = publishedAt,
    )

    private fun NearestBookingDto.toDomain(): NearestBooking {
        val dateTime = LocalDateTime.parse(bookingTime, DateTimeFormatter.ISO_LOCAL_DATE_TIME)
        return NearestBooking(
            date = dateTime.format(DateTimeFormatter.ofPattern("d MMMM yyyy", Locale("ru"))),
            time = dateTime.format(DateTimeFormatter.ofPattern("HH:mm")),
            guestsCount = guestsCount,
            qrCodeUrl = qrCodeUrl,
        )
    }

    private fun NearestBooking.toEntity() = NearestBookingEntity(
        date = date,
        time = time,
        guestsCount = guestsCount,
        qrCodeUrl = qrCodeUrl,
    )

    private fun NearestBookingEntity.toDomain() = NearestBooking(
        date = date,
        time = time,
        guestsCount = guestsCount,
        qrCodeUrl = qrCodeUrl,
    )
}
