package ru.kvartalovea.catscafe.feature.helpcat.impl.domain.usecase

import ru.kvartalovea.catscafe.feature.helpcat.impl.domain.repository.HelpCatRepository

internal class MakeDonationUseCase(
    private val repository: HelpCatRepository,
) {
    suspend operator fun invoke(catId: String, amount: Int): Result<Unit> =
        repository.makeDonation(catId = catId, amount = amount)
}
