package ru.kvartalovea.catscafe.feature.home.impl.domain.repository

import ru.kvartalovea.catscafe.feature.home.impl.domain.model.BookingResult
import ru.kvartalovea.catscafe.feature.home.impl.domain.model.NewsResult

internal interface HomeRepository {
    suspend fun getNews(): Result<NewsResult>
    suspend fun getNearestBooking(): Result<BookingResult>
}
