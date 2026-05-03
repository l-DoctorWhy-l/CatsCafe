package ru.kvartalovea.catscafe.feature.catdetails.impl.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CatDetailDto(
    @SerialName("id") val id: String,
    @SerialName("name") val name: String,
    @SerialName("image_url") val imageUrl: String? = null,
    @SerialName("is_looking_for_home") val isLookingForHome: Boolean = false,
    @SerialName("age") val age: String,
    @SerialName("breed") val breed: String,
    @SerialName("temperament") val temperament: String,
    @SerialName("bio") val bio: String,
)
