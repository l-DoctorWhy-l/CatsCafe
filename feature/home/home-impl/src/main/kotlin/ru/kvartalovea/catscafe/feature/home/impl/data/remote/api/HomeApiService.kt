package ru.kvartalovea.catscafe.feature.home.impl.data.remote.api

import retrofit2.http.GET
import ru.kvartalovea.catscafe.feature.home.impl.data.remote.dto.NearestBookingDto
import ru.kvartalovea.catscafe.feature.home.impl.data.remote.dto.NewsItemDto

internal interface HomeApiService {

    @GET("news")
    suspend fun getNews(): List<NewsItemDto>

    @GET("bookings/nearest")
    suspend fun getNearestBooking(): NearestBookingDto
}
