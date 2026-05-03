package ru.kvartalovea.catscafe.feature.helpcat.impl.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MakeDonationRequestDto(
    @SerialName("cat_id") val catId: String,
    @SerialName("amount") val amount: Int,
)
