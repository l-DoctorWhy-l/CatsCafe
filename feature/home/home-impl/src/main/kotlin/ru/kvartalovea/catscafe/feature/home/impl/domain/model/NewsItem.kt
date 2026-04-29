package ru.kvartalovea.catscafe.feature.home.impl.domain.model

data class NewsItem(
    val id: String,
    val title: String,
    val description: String,
    val imageUrl: String?,
    val publishedAt: String,
)
