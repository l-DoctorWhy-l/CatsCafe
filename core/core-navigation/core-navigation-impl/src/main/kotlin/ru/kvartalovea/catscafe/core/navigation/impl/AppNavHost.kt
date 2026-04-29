package ru.kvartalovea.catscafe.core.navigation.impl

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import org.koin.compose.getKoin
import org.koin.compose.koinInject
import ru.kvartalovea.catscafe.core.navigation.api.AppNavigator
import ru.kvartalovea.catscafe.core.navigation.api.ScreenProvider

/**
 * Корневой `NavHost` приложения.
 *
 * Собирает все [ScreenProvider]'ы из Koin (`getAll<ScreenProvider>()`) и регистрирует
 * их в графе. Привязывает `NavController` к синглтону [AppNavigator] через
 * `DisposableEffect`, чтобы любой ViewModel мог звать `navigator.navigate(route)`.
 *
 * @param navController внешний контроллер; передаётся из [MainScaffold], чтобы
 * хост и нижняя навигация использовали один и тот же back stack.
 */
@Composable
fun AppNavHost(
    startDestination: Any,
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier,
) {
    val navigator = koinInject<AppNavigator>()
    val koin = getKoin()
    val screenProviders = remember(koin) { koin.getAll<ScreenProvider>() }

    DisposableEffect(navController, navigator) {
        if (navigator is AppNavigatorImpl) {
            navigator.attach(navController)
        }
        onDispose {
            if (navigator is AppNavigatorImpl) {
                navigator.detach()
            }
        }
    }

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        screenProviders.forEach { provider ->
            with(provider) { register(navController) }
        }
    }
}
