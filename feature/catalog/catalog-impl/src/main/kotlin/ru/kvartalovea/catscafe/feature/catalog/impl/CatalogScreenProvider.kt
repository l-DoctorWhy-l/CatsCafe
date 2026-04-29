package ru.kvartalovea.catscafe.feature.catalog.impl

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ru.kvartalovea.catscafe.core.navigation.api.ScreenProvider
import ru.kvartalovea.catscafe.feature.catalog.api.CatalogRoute
import ru.kvartalovea.catscafe.feature.catalog.impl.presentation.screen.CatalogScreen

internal class CatalogScreenProvider : ScreenProvider {

    override fun NavGraphBuilder.register(navController: NavController) {
        composable<CatalogRoute> {
            CatalogScreen()
        }
    }
}
