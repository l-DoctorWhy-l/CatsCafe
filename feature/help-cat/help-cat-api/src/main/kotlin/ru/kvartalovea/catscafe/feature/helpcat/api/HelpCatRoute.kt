package ru.kvartalovea.catscafe.feature.helpcat.api

import kotlinx.serialization.Serializable

@Serializable
data class HelpCatRoute(
    val catId: String,
    val catName: String,
)
