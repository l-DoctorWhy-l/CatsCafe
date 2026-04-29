package ru.kvartalovea.catscafe.feature.donations.impl.domain.model

internal data class DonationItem(
    val id: String,
    val catName: String,
    val amount: Int,
    val date: String,
)
