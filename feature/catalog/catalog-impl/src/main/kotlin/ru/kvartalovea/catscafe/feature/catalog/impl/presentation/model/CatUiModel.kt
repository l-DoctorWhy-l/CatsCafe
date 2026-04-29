package ru.kvartalovea.catscafe.feature.catalog.impl.presentation.model

internal data class CatUiModel(
    val id: String,
    val name: String,
    val shortDescription: String,
    val ageLabel: String,
    val imageUrl: String?,
    val isNew: Boolean,
    val isLookingForHome: Boolean,
)
