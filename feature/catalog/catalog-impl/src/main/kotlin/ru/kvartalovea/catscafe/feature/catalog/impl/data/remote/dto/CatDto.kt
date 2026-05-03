package ru.kvartalovea.catscafe.feature.catalog.impl.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CatDto(
    @SerialName("id") val id: String,
    @SerialName("name") val name: String,
    @SerialName("description") val description: String,
    @SerialName("age_label") val ageLabel: String,
    @SerialName("image_url") val imageUrl: String? = null,
    @SerialName("is_new") val isNew: Boolean = false,
    @SerialName("is_looking_for_home") val isLookingForHome: Boolean = false,
)
