package ru.kvartalovea.catscafe.feature.donations.impl.domain.repository

import ru.kvartalovea.catscafe.feature.donations.impl.domain.model.DonationItem

internal interface DonationsRepository {
    suspend fun getDonations(): Result<List<DonationItem>>
}
