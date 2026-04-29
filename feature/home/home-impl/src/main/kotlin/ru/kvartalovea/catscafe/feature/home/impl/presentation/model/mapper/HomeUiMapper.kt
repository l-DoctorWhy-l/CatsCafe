package ru.kvartalovea.catscafe.feature.home.impl.presentation.model.mapper

import ru.kvartalovea.catscafe.feature.home.impl.domain.model.NearestBooking
import ru.kvartalovea.catscafe.feature.home.impl.domain.model.NewsItem
import ru.kvartalovea.catscafe.feature.home.impl.presentation.model.NearestBookingUiModel
import ru.kvartalovea.catscafe.feature.home.impl.presentation.model.NewsItemUiModel

internal fun NearestBooking.toUiModel() = NearestBookingUiModel(
    date = date,
    time = time,
    guestsCount = guestsCount,
    qrCodeUrl = qrCodeUrl,
)

internal fun NewsItem.toUiModel() = NewsItemUiModel(
    id = id,
    title = title,
    description = description,
    imageUrl = imageUrl,
    publishedAt = publishedAt,
)

internal fun List<NewsItem>.toUiModels() = map { it.toUiModel() }
