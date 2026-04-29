package ru.kvartalovea.catscafe.feature.catalog.impl.presentation.model.mapper

import ru.kvartalovea.catscafe.feature.catalog.impl.domain.model.Cat
import ru.kvartalovea.catscafe.feature.catalog.impl.presentation.model.CatUiModel

internal fun Cat.toUiModel() = CatUiModel(
    id = id,
    name = name,
    shortDescription = description,
    ageLabel = ageLabel,
    imageUrl = imageUrl,
    isNew = isNew,
    isLookingForHome = isLookingForHome,
)

internal fun List<Cat>.toUiModels() = map { it.toUiModel() }
