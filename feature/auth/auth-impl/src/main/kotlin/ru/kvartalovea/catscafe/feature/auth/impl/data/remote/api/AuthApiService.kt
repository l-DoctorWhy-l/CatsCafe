package ru.kvartalovea.catscafe.feature.auth.impl.data.remote.api

import retrofit2.http.Body
import retrofit2.http.POST
import ru.kvartalovea.catscafe.feature.auth.impl.data.remote.dto.AuthTokenDto
import ru.kvartalovea.catscafe.feature.auth.impl.data.remote.dto.LoginRequestDto
import ru.kvartalovea.catscafe.feature.auth.impl.data.remote.dto.RegisterRequestDto

internal interface AuthApiService {

    @POST("auth/login")
    suspend fun login(@Body request: LoginRequestDto): AuthTokenDto

    @POST("auth/register")
    suspend fun register(@Body request: RegisterRequestDto): AuthTokenDto
}
