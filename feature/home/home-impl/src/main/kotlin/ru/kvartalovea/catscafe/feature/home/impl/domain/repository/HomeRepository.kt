package ru.kvartalovea.catscafe.feature.home.impl.domain.repository

import ru.kvartalovea.catscafe.feature.home.impl.domain.model.NearestBooking
import ru.kvartalovea.catscafe.feature.home.impl.domain.model.NewsItem

internal interface HomeRepository {
    suspend fun getNews(): Result<List<NewsItem>>
    suspend fun getNearestBooking(): Result<NearestBooking?>
}
