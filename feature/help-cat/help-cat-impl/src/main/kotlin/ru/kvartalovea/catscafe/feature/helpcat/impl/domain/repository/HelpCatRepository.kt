package ru.kvartalovea.catscafe.feature.helpcat.impl.domain.repository

internal interface HelpCatRepository {
    suspend fun makeDonation(catId: String, amount: Int): Result<Unit>
}
