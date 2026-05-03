package ru.kvartalovea.catscafe.feature.donations.impl.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DonationItemDto(
    @SerialName("id") val id: String,
    @SerialName("cat_name") val catName: String,
    @SerialName("amount") val amount: Int,
    @SerialName("date") val date: String,
)
