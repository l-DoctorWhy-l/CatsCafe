package ru.kvartalovea.catscafe.feature.home.impl.domain.model

internal data class NewsResult(
    val items: List<NewsItem>,
    val isFromCache: Boolean,
)
