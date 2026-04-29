package ru.kvartalovea.catscafe.feature.mybookings.impl

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ru.kvartalovea.catscafe.core.navigation.api.ScreenProvider
import ru.kvartalovea.catscafe.feature.mybookings.api.MyBookingsRoute
import ru.kvartalovea.catscafe.feature.mybookings.impl.presentation.screen.MyBookingsScreen

internal class MyBookingsScreenProvider : ScreenProvider {

    override fun NavGraphBuilder.register(navController: NavController) {
        composable<MyBookingsRoute> {
            MyBookingsScreen()
        }
    }
}
