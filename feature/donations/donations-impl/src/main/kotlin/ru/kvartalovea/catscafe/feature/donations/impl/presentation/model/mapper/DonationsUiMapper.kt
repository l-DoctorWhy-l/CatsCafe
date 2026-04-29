package ru.kvartalovea.catscafe.feature.donations.impl.presentation.model.mapper

import ru.kvartalovea.catscafe.feature.donations.impl.domain.model.DonationItem
import ru.kvartalovea.catscafe.feature.donations.impl.presentation.model.DonationUiModel

internal fun DonationItem.toUiModel() = DonationUiModel(
    id = id,
    catName = catName,
    amountLabel = "$amount ₽",
    date = date,
)

internal fun List<DonationItem>.toUiModels() = map { it.toUiModel() }
