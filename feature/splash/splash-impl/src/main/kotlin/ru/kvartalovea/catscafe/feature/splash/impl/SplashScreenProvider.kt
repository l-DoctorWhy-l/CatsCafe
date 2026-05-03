package ru.kvartalovea.catscafe.feature.splash.impl

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ru.kvartalovea.catscafe.core.account.api.AccountManager
import ru.kvartalovea.catscafe.core.account.api.AuthStatus
import ru.kvartalovea.catscafe.core.navigation.api.ScreenProvider
import ru.kvartalovea.catscafe.feature.auth.api.AuthRoute
import ru.kvartalovea.catscafe.feature.home.api.HomeRoute
import ru.kvartalovea.catscafe.feature.splash.api.SplashRoute
import ru.kvartalovea.catscafe.feature.splash.impl.presentation.SplashScreen

internal class SplashScreenProvider(
    private val accountManager: AccountManager,
) : ScreenProvider {

    override fun NavGraphBuilder.register(navController: NavController) {
        composable<SplashRoute> {
            SplashScreen(
                onSplashComplete = {
                    val destination = if (accountManager.authStatus is AuthStatus.Auth) {
                        HomeRoute
                    } else {
                        AuthRoute
                    }
                    navController.navigate(destination) {
                        popUpTo<SplashRoute> { inclusive = true }
                    }
                },
            )
        }
    }
}
