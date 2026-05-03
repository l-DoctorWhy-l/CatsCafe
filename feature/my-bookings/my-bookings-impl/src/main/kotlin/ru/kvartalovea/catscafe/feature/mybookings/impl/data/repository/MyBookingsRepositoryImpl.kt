package ru.kvartalovea.catscafe.feature.mybookings.impl.data.repository

import ru.kvartalovea.catscafe.core.network.api.ApiResult
import ru.kvartalovea.catscafe.core.network.api.apiCall
import ru.kvartalovea.catscafe.feature.mybookings.impl.data.remote.api.MyBookingsApiService
import ru.kvartalovea.catscafe.feature.mybookings.impl.data.remote.dto.UserBookingDto
import ru.kvartalovea.catscafe.feature.mybookings.impl.domain.model.UserBooking
import ru.kvartalovea.catscafe.feature.mybookings.impl.domain.model.UserBookingStatus
import ru.kvartalovea.catscafe.feature.mybookings.impl.domain.repository.MyBookingsRepository
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

internal class MyBookingsRepositoryImpl(
    private val api: MyBookingsApiService,
) : MyBookingsRepository {

    override suspend fun getMyBookings(): Result<List<UserBooking>> =
        apiCall { api.getMyBookings().map { it.toDomain() } }.toResult()

    private fun UserBookingDto.toDomain(): UserBooking {
        val dateTime = LocalDateTime.parse(bookingTime, DateTimeFormatter.ISO_LOCAL_DATE_TIME)
        return UserBooking(
            id = id,
            date = dateTime.format(DateTimeFormatter.ofPattern("d MMMM yyyy", Locale("ru"))),
            time = dateTime.format(DateTimeFormatter.ofPattern("HH:mm")),
            guestsCount = guestsCount,
            status = status.toStatus(),
        )
    }

    private fun String.toStatus(): UserBookingStatus = when (this) {
        "upcoming" -> UserBookingStatus.Upcoming
        "past" -> UserBookingStatus.Past
        "cancelled" -> UserBookingStatus.Cancelled
        else -> UserBookingStatus.Past
    }

    private fun <T> ApiResult<T>.toResult(): Result<T> = when (this) {
        is ApiResult.Success -> Result.success(data)
        is ApiResult.Failure.Http -> Result.failure(Exception("HTTP $code: $message"))
        is ApiResult.Failure.Network -> Result.failure(Exception("Нет подключения к сети"))
        is ApiResult.Failure.Timeout -> Result.failure(Exception("Превышено время ожидания"))
        is ApiResult.Failure.Serialization -> Result.failure(cause)
        is ApiResult.Failure.Unknown -> Result.failure(cause)
    }
}
