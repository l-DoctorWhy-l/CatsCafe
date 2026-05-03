package ru.kvartalovea.catscafe.core.account.impl

import android.content.Context
import android.content.SharedPreferences
import ru.kvartalovea.catscafe.core.account.api.AccountManager
import ru.kvartalovea.catscafe.core.account.api.AuthStatus
import ru.kvartalovea.catscafe.core.account.api.User

internal class AccountManagerImpl(context: Context) : AccountManager {

    private val prefs: SharedPreferences =
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    override val authStatus: AuthStatus
        get() {
            val token = prefs.getString(KEY_TOKEN, null)
            val name = prefs.getString(KEY_USER_NAME, null)
            val email = prefs.getString(KEY_USER_EMAIL, null)
            return if (token != null && name != null && email != null) {
                AuthStatus.Auth(
                    User(
                        name = name,
                        email = email,
                        loyaltyPoints = prefs.getInt(KEY_USER_LOYALTY, 0),
                    ),
                )
            } else {
                AuthStatus.NoAuth
            }
        }

    override fun saveToken(token: String) {
        prefs.edit().putString(KEY_TOKEN, token).apply()
    }

    override fun getToken(): String? = prefs.getString(KEY_TOKEN, null)

    override fun setUser(user: User) {
        prefs.edit()
            .putString(KEY_USER_NAME, user.name)
            .putString(KEY_USER_EMAIL, user.email)
            .putInt(KEY_USER_LOYALTY, user.loyaltyPoints)
            .apply()
    }

    override fun logout() {
        prefs.edit().clear().apply()
    }

    private companion object {
        const val PREFS_NAME = "account_prefs"
        const val KEY_TOKEN = "token"
        const val KEY_USER_NAME = "user_name"
        const val KEY_USER_EMAIL = "user_email"
        const val KEY_USER_LOYALTY = "user_loyalty"
    }
}
