package ru.kvartalovea.catscafe.feature.auth.impl.domain.repository

internal interface AuthRepository {
    suspend fun login(email: String, password: String): Result<Unit>
    suspend fun register(name: String, email: String, password: String): Result<Unit>
}
