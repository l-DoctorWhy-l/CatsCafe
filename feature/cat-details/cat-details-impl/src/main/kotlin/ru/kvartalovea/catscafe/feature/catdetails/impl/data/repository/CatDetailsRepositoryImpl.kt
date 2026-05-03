package ru.kvartalovea.catscafe.feature.catdetails.impl.data.repository

import ru.kvartalovea.catscafe.core.network.api.ApiResult
import ru.kvartalovea.catscafe.core.network.api.apiCall
import ru.kvartalovea.catscafe.feature.catdetails.impl.data.remote.api.CatDetailsApiService
import ru.kvartalovea.catscafe.feature.catdetails.impl.data.remote.dto.CatDetailDto
import ru.kvartalovea.catscafe.feature.catdetails.impl.domain.model.CatDetail
import ru.kvartalovea.catscafe.feature.catdetails.impl.domain.repository.CatDetailsRepository

internal class CatDetailsRepositoryImpl(
    private val api: CatDetailsApiService,
) : CatDetailsRepository {

    override suspend fun getCatById(id: String): Result<CatDetail?> =
        when (val result = apiCall { api.getCatById(id) }) {
            is ApiResult.Success -> Result.success(result.data.toDomain())
            is ApiResult.Failure.Http -> if (result.code == 404) {
                Result.success(null)
            } else {
                Result.failure(Exception("HTTP ${result.code}: ${result.message}"))
            }
            is ApiResult.Failure.Network -> Result.failure(Exception("Нет подключения к сети"))
            is ApiResult.Failure.Timeout -> Result.failure(Exception("Превышено время ожидания"))
            is ApiResult.Failure.Serialization -> Result.failure(result.cause)
            is ApiResult.Failure.Unknown -> Result.failure(result.cause)
        }

    private fun CatDetailDto.toDomain() = CatDetail(
        id = id,
        name = name,
        imageUrl = imageUrl,
        isLookingForHome = isLookingForHome,
        age = age,
        breed = breed,
        temperament = temperament,
        bio = bio,
    )
}
