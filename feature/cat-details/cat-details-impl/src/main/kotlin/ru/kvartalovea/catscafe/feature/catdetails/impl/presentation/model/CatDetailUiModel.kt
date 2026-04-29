package ru.kvartalovea.catscafe.feature.catdetails.impl.presentation.model

internal data class CatDetailUiModel(
    val id: String,
    val name: String,
    val imageUrl: String?,
    val isLookingForHome: Boolean,
    val age: String,
    val breed: String,
    val temperament: String,
    val bio: String,
)
