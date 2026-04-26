package ru.kvartalovea.catscafe.feature.catdetails.impl

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import ru.kvartalovea.catscafe.core.navigation.api.ScreenProvider
import ru.kvartalovea.catscafe.feature.catdetails.api.CatDetailsRoute
import ru.kvartalovea.catscafe.feature.catdetails.impl.presentation.CatDetailsScreen

internal class CatDetailsScreenProvider : ScreenProvider {

    override fun NavGraphBuilder.register(navController: NavController) {
        composable<CatDetailsRoute> { backStackEntry ->
            val route: CatDetailsRoute = backStackEntry.toRoute()
            CatDetailsScreen(catId = route.catId)
        }
    }
}
