package ru.kvartalovea.catscafe.feature.donations.impl.data.repository

import kotlinx.coroutines.delay
import ru.kvartalovea.catscafe.feature.donations.impl.domain.model.DonationItem
import ru.kvartalovea.catscafe.feature.donations.impl.domain.repository.DonationsRepository

internal class DonationsRepositoryMock : DonationsRepository {

    override suspend fun getDonations(): Result<List<DonationItem>> {
        delay(MOCK_DELAY_MS)
        return Result.success(MOCK_DONATIONS)
    }

    private companion object {
        const val MOCK_DELAY_MS = 500L

        val MOCK_DONATIONS = listOf(
            DonationItem(
                id = "d1",
                catName = "Мурзик",
                amount = 150,
                date = "28 апреля 2026",
            ),
            DonationItem(
                id = "d2",
                catName = "Луна",
                amount = 300,
                date = "15 апреля 2026",
            ),
            DonationItem(
                id = "d3",
                catName = "Снежинка",
                amount = 100,
                date = "2 марта 2026",
            ),
            DonationItem(
                id = "d4",
                catName = "Барсик",
                amount = 200,
                date = "14 февраля 2026",
            ),
        )
    }
}
