package ru.kvartalovea.catscafe.feature.auth.impl.domain.usecase

import ru.kvartalovea.catscafe.feature.auth.impl.domain.repository.AuthRepository

internal class LoginUseCase(private val repository: AuthRepository) {
    suspend operator fun invoke(email: String, password: String): Result<Unit> =
        repository.login(email, password)
}
