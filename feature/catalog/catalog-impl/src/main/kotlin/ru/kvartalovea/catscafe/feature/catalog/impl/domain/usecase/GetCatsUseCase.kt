package ru.kvartalovea.catscafe.feature.catalog.impl.domain.usecase

import ru.kvartalovea.catscafe.feature.catalog.impl.domain.model.Cat
import ru.kvartalovea.catscafe.feature.catalog.impl.domain.repository.CatalogRepository

internal class GetCatsUseCase(private val repository: CatalogRepository) {
    suspend operator fun invoke(): Result<List<Cat>> = repository.getCats()
}
