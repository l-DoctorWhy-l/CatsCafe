package ru.kvartalovea.catscafe.feature.donations.impl.domain.usecase

import ru.kvartalovea.catscafe.feature.donations.impl.domain.model.DonationItem
import ru.kvartalovea.catscafe.feature.donations.impl.domain.repository.DonationsRepository

internal class GetDonationsUseCase(private val repository: DonationsRepository) {
    suspend operator fun invoke(): Result<List<DonationItem>> = repository.getDonations()
}
