package ru.kvartalovea.catscafe.feature.home.impl

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ru.kvartalovea.catscafe.core.navigation.api.ScreenProvider
import ru.kvartalovea.catscafe.feature.home.api.HomeRoute
import ru.kvartalovea.catscafe.feature.home.impl.presentation.HomeScreen

internal class HomeScreenProvider : ScreenProvider {

    override fun NavGraphBuilder.register(navController: NavController) {
        composable<HomeRoute> {
            HomeScreen()
        }
    }
}
