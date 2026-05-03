package ru.kvartalovea.catscafe.feature.auth.impl.data.repository

import kotlinx.coroutines.delay
import ru.kvartalovea.catscafe.feature.auth.impl.domain.repository.AuthRepository

internal class AuthRepositoryMock : AuthRepository {

    override suspend fun login(email: String, password: String): Result<Unit> {
        delay(MOCK_DELAY_MS)
        return Result.success(Unit)
    }

    override suspend fun register(name: String, email: String, password: String): Result<Unit> {
        delay(MOCK_DELAY_MS)
        return Result.success(Unit)
    }

    private companion object {
        const val MOCK_DELAY_MS = 800L
    }
}
