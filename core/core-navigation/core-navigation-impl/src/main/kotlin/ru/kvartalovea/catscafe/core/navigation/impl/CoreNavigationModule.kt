package ru.kvartalovea.catscafe.core.navigation.impl

import org.koin.dsl.module
import ru.kvartalovea.catscafe.core.navigation.api.AppNavigator

/**
 * Koin модуль `core-navigation-impl`.
 *
 * Регистрирует синглтон [AppNavigator]. `ScreenProvider`'ы регистрируются в
 * соответствующих `feature-X-impl` модулях и подбираются `AppNavHost`'ом
 * через `getAll<ScreenProvider>()`.
 */
val coreNavigationModule = module {
    single<AppNavigator> { AppNavigatorImpl() }
}
