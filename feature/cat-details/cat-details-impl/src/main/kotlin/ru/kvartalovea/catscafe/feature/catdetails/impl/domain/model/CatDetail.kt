package ru.kvartalovea.catscafe.feature.catdetails.impl.domain.model

internal data class CatDetail(
    val id: String,
    val name: String,
    val imageUrl: String?,
    val isLookingForHome: Boolean,
    val age: String,
    val breed: String,
    val temperament: String,
    val bio: String,
)
