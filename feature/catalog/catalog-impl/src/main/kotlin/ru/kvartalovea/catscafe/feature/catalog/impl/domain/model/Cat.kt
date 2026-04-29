package ru.kvartalovea.catscafe.feature.catalog.impl.domain.model

data class Cat(
    val id: String,
    val name: String,
    val description: String,
    val ageLabel: String,
    val imageUrl: String?,
    val isNew: Boolean,
    val isLookingForHome: Boolean,
)
