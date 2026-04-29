package ru.kvartalovea.catscafe.core.account.api

interface AccountManager {

    val authStatus: AuthStatus

    fun getCurrentUser(): User? = (authStatus as? AuthStatus.Auth)?.user

    fun logout()
}
