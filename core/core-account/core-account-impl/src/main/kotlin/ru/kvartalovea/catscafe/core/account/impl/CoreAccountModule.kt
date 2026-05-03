package ru.kvartalovea.catscafe.core.account.impl

import android.content.Context
import org.koin.core.module.Module
import org.koin.dsl.module
import ru.kvartalovea.catscafe.core.account.api.AccountManager

fun createAccountManager(context: Context): AccountManager = AccountManagerImpl(context)

fun coreAccountModule(accountManager: AccountManager): Module = module {
    single<AccountManager> { accountManager }
}
