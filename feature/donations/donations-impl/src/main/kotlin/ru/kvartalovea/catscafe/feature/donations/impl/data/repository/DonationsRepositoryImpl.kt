package ru.kvartalovea.catscafe.feature.donations.impl.data.repository

import ru.kvartalovea.catscafe.core.network.api.ApiResult
import ru.kvartalovea.catscafe.core.network.api.apiCall
import ru.kvartalovea.catscafe.feature.donations.impl.data.remote.api.DonationsApiService
import ru.kvartalovea.catscafe.feature.donations.impl.data.remote.dto.DonationItemDto
import ru.kvartalovea.catscafe.feature.donations.impl.domain.model.DonationItem
import ru.kvartalovea.catscafe.feature.donations.impl.domain.repository.DonationsRepository

internal class DonationsRepositoryImpl(
    private val api: DonationsApiService,
) : DonationsRepository {

    override suspend fun getDonations(): Result<List<DonationItem>> =
        apiCall { api.getDonations().map { it.toDomain() } }.toResult()

    private fun DonationItemDto.toDomain() = DonationItem(
        id = id,
        catName = catName,
        amount = amount,
        date = date,
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
