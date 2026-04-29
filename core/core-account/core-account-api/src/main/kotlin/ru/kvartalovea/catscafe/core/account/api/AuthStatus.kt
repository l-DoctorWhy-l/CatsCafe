package ru.kvartalovea.catscafe.core.account.api

sealed interface AuthStatus {
    data object NoAuth : AuthStatus
    data class Auth(val user: User) : AuthStatus
}
