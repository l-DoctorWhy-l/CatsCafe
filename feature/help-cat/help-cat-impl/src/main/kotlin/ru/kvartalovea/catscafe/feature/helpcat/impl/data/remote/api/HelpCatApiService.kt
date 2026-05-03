package ru.kvartalovea.catscafe.feature.helpcat.impl.data.remote.api

import retrofit2.http.Body
import retrofit2.http.POST
import ru.kvartalovea.catscafe.feature.helpcat.impl.data.remote.dto.MakeDonationRequestDto

internal interface HelpCatApiService {

    @POST("donations")
    suspend fun makeDonation(@Body request: MakeDonationRequestDto)
}
