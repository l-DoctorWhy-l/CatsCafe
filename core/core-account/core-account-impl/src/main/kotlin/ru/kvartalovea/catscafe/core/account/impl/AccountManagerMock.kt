package ru.kvartalovea.catscafe.core.account.impl

import ru.kvartalovea.catscafe.core.account.api.AccountManager
import ru.kvartalovea.catscafe.core.account.api.AuthStatus
import ru.kvartalovea.catscafe.core.account.api.User

/**
 * Заглушка [AccountManager] до появления реального auth-слоя.
 *
 * TODO: заменить на реальную реализацию после появления auth-слоя.
 */
internal class AccountManagerMock : AccountManager {

    private val mockUser = User(
        name = "Егор Кварталов",
        email = "kvartalovfade@gmail.com",
        loyaltyPoints = 450,
    )

    override val authStatus: AuthStatus = AuthStatus.Auth(mockUser)

    override fun logout() = Unit
}
