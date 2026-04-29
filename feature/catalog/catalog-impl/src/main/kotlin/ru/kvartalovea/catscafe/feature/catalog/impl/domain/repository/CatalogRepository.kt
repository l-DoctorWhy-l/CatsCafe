package ru.kvartalovea.catscafe.feature.catalog.impl.domain.repository

import ru.kvartalovea.catscafe.feature.catalog.impl.domain.model.Cat

internal interface CatalogRepository {
    suspend fun getCats(): Result<List<Cat>>
}
