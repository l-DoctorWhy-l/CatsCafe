package ru.kvartalovea.catscafe.feature.catalog.impl.data.remote.api

import retrofit2.http.GET
import ru.kvartalovea.catscafe.feature.catalog.impl.data.remote.dto.CatDto

internal interface CatalogApiService {

    @GET("cats")
    suspend fun getCats(): List<CatDto>
}
