package ru.kvartalovea.catscafe.feature.catdetails.impl.domain.usecase

import ru.kvartalovea.catscafe.feature.catdetails.impl.domain.model.CatDetail
import ru.kvartalovea.catscafe.feature.catdetails.impl.domain.repository.CatDetailsRepository

internal class GetCatDetailsUseCase(private val repository: CatDetailsRepository) {
    suspend operator fun invoke(catId: String): Result<CatDetail?> =
        repository.getCatById(catId)
}
