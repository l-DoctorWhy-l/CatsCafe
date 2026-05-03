package ru.kvartalovea.catscafe.feature.home.impl.domain.usecase

import ru.kvartalovea.catscafe.feature.home.impl.domain.model.NewsResult
import ru.kvartalovea.catscafe.feature.home.impl.domain.repository.HomeRepository

internal class GetNewsUseCase(private val repository: HomeRepository) {
    suspend operator fun invoke(): Result<NewsResult> = repository.getNews()
}
