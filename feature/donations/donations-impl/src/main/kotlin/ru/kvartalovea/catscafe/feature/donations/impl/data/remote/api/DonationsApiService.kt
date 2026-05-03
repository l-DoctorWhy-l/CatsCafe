package ru.kvartalovea.catscafe.feature.donations.impl.data.remote.api

import retrofit2.http.GET
import ru.kvartalovea.catscafe.feature.donations.impl.data.remote.dto.DonationItemDto

internal interface DonationsApiService {

    @GET("donations")
    suspend fun getDonations(): List<DonationItemDto>
}
