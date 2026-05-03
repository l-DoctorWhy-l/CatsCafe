package ru.kvartalovea.catscafe.feature.auth.impl.domain.usecase

import ru.kvartalovea.catscafe.feature.auth.impl.domain.repository.AuthRepository

internal class RegisterUseCase(private val repository: AuthRepository) {
    suspend operator fun invoke(name: String, email: String, password: String): Result<Unit> =
        repository.register(name, email, password)
}
