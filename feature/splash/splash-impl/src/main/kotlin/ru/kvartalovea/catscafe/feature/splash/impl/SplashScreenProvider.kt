package ru.kvartalovea.catscafe.feature.splash.impl

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ru.kvartalovea.catscafe.core.navigation.api.ScreenProvider
import ru.kvartalovea.catscafe.feature.splash.api.SplashRoute
import ru.kvartalovea.catscafe.feature.splash.impl.presentation.SplashScreen

internal class SplashScreenProvider : ScreenProvider {

    override fun NavGraphBuilder.register(navController: NavController) {
        composable<SplashRoute> {
            SplashScreen()
        }
    }
}
