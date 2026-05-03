package ru.kvartalovea.catscafe.feature.auth.impl

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ru.kvartalovea.catscafe.core.navigation.api.ScreenProvider
import ru.kvartalovea.catscafe.feature.auth.api.AuthRoute
import ru.kvartalovea.catscafe.feature.auth.impl.presentation.screen.AuthScreen
import ru.kvartalovea.catscafe.feature.home.api.HomeRoute

internal class AuthScreenProvider : ScreenProvider {

    override fun NavGraphBuilder.register(navController: NavController) {
        composable<AuthRoute> {
            AuthScreen(
                onAuthSuccess = {
                    navController.navigate(HomeRoute) {
                        popUpTo<AuthRoute> { inclusive = true }
                    }
                },
            )
        }
    }
}
