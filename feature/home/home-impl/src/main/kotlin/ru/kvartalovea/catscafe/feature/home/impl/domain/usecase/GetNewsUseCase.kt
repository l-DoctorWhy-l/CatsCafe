package ru.kvartalovea.catscafe.feature.home.impl.domain.usecase

import ru.kvartalovea.catscafe.feature.home.impl.domain.model.NewsItem
import ru.kvartalovea.catscafe.feature.home.impl.domain.repository.HomeRepository

internal class GetNewsUseCase(private val repository: HomeRepository) {
    suspend operator fun invoke(): Result<List<NewsItem>> = repository.getNews()
}
