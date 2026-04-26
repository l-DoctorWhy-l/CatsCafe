package ru.kvartalovea.catscafe.core.navigation.impl

import androidx.navigation.NavController
import androidx.navigation.NavOptionsBuilder
import ru.kvartalovea.catscafe.core.navigation.api.AppNavigator

/**
 * Реализация [AppNavigator] поверх Compose [NavController].
 *
 * `NavController` живёт в Composition (`rememberNavController`), поэтому
 * привязка/отвязка контроллера выполняется в [AppNavHost] через `DisposableEffect`.
 * До привязки методы навигатора no-op.
 */
internal class AppNavigatorImpl : AppNavigator {

    @Volatile
    private var controller: NavController? = null

    fun attach(controller: NavController) {
        this.controller = controller
    }

    fun detach() {
        this.controller = null
    }

    override fun navigate(route: Any) {
        controller?.navigate(route)
    }

    fun navigate(route: Any, builder: NavOptionsBuilder.() -> Unit) {
        controller?.navigate(route, builder)
    }

    override fun popBackStack() {
        controller?.popBackStack()
    }

    override fun popBackStackTo(route: Any, inclusive: Boolean) {
        controller?.popBackStack(route, inclusive)
    }
}
