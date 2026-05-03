package ru.kvartalovea.catscafe.feature.catalog.impl.data.repository

import ru.kvartalovea.catscafe.core.network.api.ApiResult
import ru.kvartalovea.catscafe.core.network.api.apiCall
import ru.kvartalovea.catscafe.feature.catalog.impl.data.remote.api.CatalogApiService
import ru.kvartalovea.catscafe.feature.catalog.impl.data.remote.dto.CatDto
import ru.kvartalovea.catscafe.feature.catalog.impl.domain.model.Cat
import ru.kvartalovea.catscafe.feature.catalog.impl.domain.repository.CatalogRepository

internal class CatalogRepositoryImpl(
    private val api: CatalogApiService,
) : CatalogRepository {

    override suspend fun getCats(): Result<List<Cat>> =
        apiCall { api.getCats().map { it.toDomain() } }.toResult()

    private fun CatDto.toDomain() = Cat(
        id = id,
        name = name,
        description = description,
        ageLabel = ageLabel,
        imageUrl = imageUrl,
        isNew = isNew,
        isLookingForHome = isLookingForHome,
    )

    private fun <T> ApiResult<T>.toResult(): Result<T> = when (this) {
        is ApiResult.Success -> Result.success(data)
        is ApiResult.Failure.Http -> Result.failure(Exception("HTTP $code: $message"))
        is ApiResult.Failure.Network -> Result.failure(Exception("Нет подключения к сети"))
        is ApiResult.Failure.Timeout -> Result.failure(Exception("Превышено время ожидания"))
        is ApiResult.Failure.Serialization -> Result.failure(cause)
        is ApiResult.Failure.Unknown -> Result.failure(cause)
    }
}
