package ru.kvartalovea.catscafe.feature.catdetails.impl.presentation.model.mapper

import ru.kvartalovea.catscafe.feature.catdetails.impl.domain.model.CatDetail
import ru.kvartalovea.catscafe.feature.catdetails.impl.presentation.model.CatDetailUiModel

internal fun CatDetail.toUiModel() = CatDetailUiModel(
    id = id,
    name = name,
    imageUrl = imageUrl,
    isLookingForHome = isLookingForHome,
    age = age,
    breed = breed,
    temperament = temperament,
    bio = bio,
)
