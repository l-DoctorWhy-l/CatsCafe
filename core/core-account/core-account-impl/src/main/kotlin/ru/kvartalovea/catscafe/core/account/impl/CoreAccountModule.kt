package ru.kvartalovea.catscafe.core.account.impl

import org.koin.dsl.module
import ru.kvartalovea.catscafe.core.account.api.AccountManager

val coreAccountModule = module {
    single<AccountManager> { AccountManagerMock() }
}
