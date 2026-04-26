package ru.kvartalovea.catscafe.core.navigation.api

/**
 * Абстракция навигации для всех модулей.
 *
 * Принимает type-safe маршруты — `@Serializable` data class'ы или object'ы,
 * объявленные в `feature-X-api`. Реализация инкапсулирует `NavController`
 * (живёт только в `core-navigation-impl`).
 */
interface AppNavigator {

    /** Перейти к маршруту. */
    fun navigate(route: Any)

    /** Откатить один шаг назад в back stack. */
    fun popBackStack()

    /**
     * Откатить back stack до указанного маршрута.
     *
     * @param inclusive если true — удалить и сам маршрут [route].
     */
    fun popBackStackTo(route: Any, inclusive: Boolean = false)
}
