package ru.kvartalovea.catscafe.feature.helpcat.impl.data.repository

import ru.kvartalovea.catscafe.core.network.api.ApiResult
import ru.kvartalovea.catscafe.core.network.api.apiCall
import ru.kvartalovea.catscafe.feature.helpcat.impl.data.remote.api.HelpCatApiService
import ru.kvartalovea.catscafe.feature.helpcat.impl.data.remote.dto.MakeDonationRequestDto
import ru.kvartalovea.catscafe.feature.helpcat.impl.domain.repository.HelpCatRepository

internal class HelpCatRepositoryImpl(
    private val api: HelpCatApiService,
) : HelpCatRepository {

    override suspend fun makeDonation(catId: String, amount: Int): Result<Unit> =
        apiCall {
            api.makeDonation(MakeDonationRequestDto(catId = catId, amount = amount))
        }.toUnitResult()

    private fun <T> ApiResult<T>.toUnitResult(): Result<Unit> = when (this) {
        is ApiResult.Success -> Result.success(Unit)
        is ApiResult.Failure.Http -> Result.failure(Exception("HTTP $code: $message"))
        is ApiResult.Failure.Network -> Result.failure(Exception("Нет подключения к сети"))
        is ApiResult.Failure.Timeout -> Result.failure(Exception("Превышено время ожидания"))
        is ApiResult.Failure.Serialization -> Result.failure(cause)
        is ApiResult.Failure.Unknown -> Result.failure(cause)
    }
}
