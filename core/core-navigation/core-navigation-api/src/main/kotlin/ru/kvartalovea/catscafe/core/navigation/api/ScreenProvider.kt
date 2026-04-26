package ru.kvartalovea.catscafe.core.navigation.api

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder

/**
 * Контракт регистрации экрана в общем `NavHost`.
 *
 * Реализации живут **только** в `feature-X-impl` модулях и регистрируются в Koin
 * как `single { ... } bind ScreenProvider::class`. `core-navigation-impl` собирает
 * всех провайдеров через `getAll<ScreenProvider>()` и вызывает [register] у каждого
 * при сборке графа.
 */
interface ScreenProvider {
    fun NavGraphBuilder.register(navController: NavController)
}
