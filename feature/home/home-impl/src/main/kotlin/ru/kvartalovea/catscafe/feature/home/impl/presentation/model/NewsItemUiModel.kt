package ru.kvartalovea.catscafe.feature.home.impl.presentation.model

internal data class NewsItemUiModel(
    val id: String,
    val title: String,
    val description: String,
    val imageUrl: String?,
    val publishedAt: String,
)
