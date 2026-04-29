package ru.kvartalovea.catscafe.feature.donations.impl

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ru.kvartalovea.catscafe.core.navigation.api.ScreenProvider
import ru.kvartalovea.catscafe.feature.donations.api.DonationsRoute
import ru.kvartalovea.catscafe.feature.donations.impl.presentation.screen.DonationsScreen

internal class DonationsScreenProvider : ScreenProvider {

    override fun NavGraphBuilder.register(navController: NavController) {
        composable<DonationsRoute> {
            DonationsScreen()
        }
    }
}
