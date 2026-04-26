package ru.kvartalovea.catscafe.di

import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import ru.kvartalovea.catscafe.core.navigation.api.ScreenProvider
import ru.kvartalovea.catscafe.navigation.HomeScreenProvider

/**
 * Koin модуль уровня `:app`.
 *
 * Здесь живут только app-specific биндинги: например, временный стартовый
 * `ScreenProvider`. Когда появится `:feature:home:home-impl`, заглушку отсюда
 * нужно будет убрать, а её модуль подключить в [CatsCafeApplication].
 */
val appModule = module {
    singleOf(::HomeScreenProvider) { bind<ScreenProvider>() }
}
