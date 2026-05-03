package ru.kvartalovea.catscafe.feature.helpcat.impl

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import ru.kvartalovea.catscafe.core.navigation.api.ScreenProvider
import ru.kvartalovea.catscafe.feature.helpcat.api.HelpCatRoute
import ru.kvartalovea.catscafe.feature.helpcat.impl.presentation.screen.HelpCatScreen

internal class HelpCatScreenProvider : ScreenProvider {

    override fun NavGraphBuilder.register(navController: NavController) {
        composable<HelpCatRoute> { backStack ->
            val route = backStack.toRoute<HelpCatRoute>()
            HelpCatScreen(
                catId = route.catId,
                catName = route.catName,
            )
        }
    }
}
