package ru.kvartalovea.catscafe.feature.auth.impl.data.repository

import ru.kvartalovea.catscafe.core.account.api.AccountManager
import ru.kvartalovea.catscafe.core.account.api.User
import ru.kvartalovea.catscafe.core.network.api.ApiResult
import ru.kvartalovea.catscafe.core.network.api.apiCall
import ru.kvartalovea.catscafe.feature.auth.impl.data.remote.api.AuthApiService
import ru.kvartalovea.catscafe.feature.auth.impl.data.remote.dto.AuthTokenDto
import ru.kvartalovea.catscafe.feature.auth.impl.data.remote.dto.LoginRequestDto
import ru.kvartalovea.catscafe.feature.auth.impl.data.remote.dto.RegisterRequestDto
import ru.kvartalovea.catscafe.feature.auth.impl.domain.repository.AuthRepository

internal class AuthRepositoryImpl(
    private val api: AuthApiService,
    private val accountManager: AccountManager,
) : AuthRepository {

    override suspend fun login(email: String, password: String): Result<Unit> {
        val result = apiCall { api.login(LoginRequestDto(email = email, password = password)) }
        if (result is ApiResult.Success) {
            result.data.persist(name = "", email = email)
        }
        return result.toUnitResult()
    }

    override suspend fun register(name: String, email: String, password: String): Result<Unit> {
        val result = apiCall {
            api.register(RegisterRequestDto(name = name, email = email, password = password))
        }
        if (result is ApiResult.Success) {
            result.data.persist(name = name, email = email)
        }
        return result.toUnitResult()
    }

    private fun AuthTokenDto.persist(name: String, email: String) {
        accountManager.saveToken(accessToken)
        accountManager.setUser(User(name = name, email = email, loyaltyPoints = 0))
    }

    private fun <T> ApiResult<T>.toUnitResult(): Result<Unit> = when (this) {
        is ApiResult.Success -> Result.success(Unit)
        is ApiResult.Failure.Http -> Result.failure(Exception("HTTP $code: $message"))
        is ApiResult.Failure.Network -> Result.failure(Exception("Нет подключения к сети"))
        is ApiResult.Failure.Timeout -> Result.failure(Exception("Превышено время ожидания"))
        is ApiResult.Failure.Serialization -> Result.failure(cause)
        is ApiResult.Failure.Unknown -> Result.failure(cause)
    }
}
