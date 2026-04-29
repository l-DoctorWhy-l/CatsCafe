package ru.kvartalovea.catscafe.feature.catdetails.impl.domain.repository

import ru.kvartalovea.catscafe.feature.catdetails.impl.domain.model.CatDetail

internal interface CatDetailsRepository {
    suspend fun getCatById(id: String): Result<CatDetail?>
}
