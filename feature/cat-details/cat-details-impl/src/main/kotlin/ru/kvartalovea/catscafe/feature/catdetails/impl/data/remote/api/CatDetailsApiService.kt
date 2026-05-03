package ru.kvartalovea.catscafe.feature.catdetails.impl.data.remote.api

import retrofit2.http.GET
import retrofit2.http.Path
import ru.kvartalovea.catscafe.feature.catdetails.impl.data.remote.dto.CatDetailDto

internal interface CatDetailsApiService {

    @GET("cats/{id}")
    suspend fun getCatById(@Path("id") id: String): CatDetailDto
}
