package ru.kvartalovea.catscafe.feature.booking.impl.data.repository

import ru.kvartalovea.catscafe.core.network.api.ApiResult
import ru.kvartalovea.catscafe.core.network.api.apiCall
import ru.kvartalovea.catscafe.feature.booking.impl.data.remote.api.BookingApiService
import ru.kvartalovea.catscafe.feature.booking.impl.data.remote.dto.CreateBookingRequestDto
import ru.kvartalovea.catscafe.feature.booking.impl.data.remote.dto.TimeSlotDto
import ru.kvartalovea.catscafe.feature.booking.impl.domain.model.TimeSlot
import ru.kvartalovea.catscafe.feature.booking.impl.domain.repository.BookingRepository

internal class BookingRepositoryImpl(
    private val api: BookingApiService,
) : BookingRepository {

    override suspend fun getAvailableSlots(date: String): Result<List<TimeSlot>> =
        apiCall { api.getAvailableSlots(date).map { it.toDomain() } }.toResult()

    override suspend fun createBooking(
        date: String,
        time: String,
        guestsCount: Int,
    ): Result<Unit> =
        apiCall {
            // date приходит в формате "YYYY-MM-DD" (LocalDate.toString()),
            // time — "HH:mm"; собираем ISO 8601 для передачи на сервер
            val bookingTime = "${date}T${time}:00"
            api.createBooking(
                CreateBookingRequestDto(bookingTime = bookingTime, guestsCount = guestsCount),
            )
        }.toUnitResult()

    private fun TimeSlotDto.toDomain() = TimeSlot(
        time = time,
        isAvailable = isAvailable,
    )

    private fun <T> ApiResult<T>.toResult(): Result<T> = when (this) {
        is ApiResult.Success -> Result.success(data)
        is ApiResult.Failure.Http -> Result.failure(Exception("HTTP $code: $message"))
        is ApiResult.Failure.Network -> Result.failure(Exception("Нет подключения к сети"))
        is ApiResult.Failure.Timeout -> Result.failure(Exception("Превышено время ожидания"))
        is ApiResult.Failure.Serialization -> Result.failure(cause)
        is ApiResult.Failure.Unknown -> Result.failure(cause)
    }

    private fun <T> ApiResult<T>.toUnitResult(): Result<Unit> = when (this) {
        is ApiResult.Success -> Result.success(Unit)
        is ApiResult.Failure.Http -> Result.failure(Exception("HTTP $code: $message"))
        is ApiResult.Failure.Network -> Result.failure(Exception("Нет подключения к сети"))
        is ApiResult.Failure.Timeout -> Result.failure(Exception("Превышено время ожидания"))
        is ApiResult.Failure.Serialization -> Result.failure(cause)
        is ApiResult.Failure.Unknown -> Result.failure(cause)
    }
}
