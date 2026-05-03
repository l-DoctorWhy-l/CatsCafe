package ru.kvartalovea.catscafe.feature.mybookings.impl.data.remote.api

import retrofit2.http.GET
import ru.kvartalovea.catscafe.feature.mybookings.impl.data.remote.dto.UserBookingDto

internal interface MyBookingsApiService {

    @GET("bookings/my")
    suspend fun getMyBookings(): List<UserBookingDto>
}
