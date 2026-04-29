package ru.kvartalovea.catscafe.core.account.api

data class User(
    val name: String,
    val email: String,
    val loyaltyPoints: Int,
)
