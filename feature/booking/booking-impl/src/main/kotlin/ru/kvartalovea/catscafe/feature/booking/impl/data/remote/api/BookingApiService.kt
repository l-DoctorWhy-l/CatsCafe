package ru.kvartalovea.catscafe.feature.booking.impl.data.remote.api

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import ru.kvartalovea.catscafe.feature.booking.impl.data.remote.dto.CreateBookingRequestDto
import ru.kvartalovea.catscafe.feature.booking.impl.data.remote.dto.TimeSlotDto

internal interface BookingApiService {

    @GET("slots")
    suspend fun getAvailableSlots(@Query("date") date: String): List<TimeSlotDto>

    @POST("bookings")
    suspend fun createBooking(@Body request: CreateBookingRequestDto)
}
