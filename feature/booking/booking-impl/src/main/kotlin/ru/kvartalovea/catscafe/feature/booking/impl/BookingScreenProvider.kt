package ru.kvartalovea.catscafe.feature.booking.impl

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import ru.kvartalovea.catscafe.core.navigation.api.ScreenProvider
import ru.kvartalovea.catscafe.feature.booking.api.BookingRoute
import ru.kvartalovea.catscafe.feature.booking.impl.presentation.screen.BookingScreen

internal class BookingScreenProvider : ScreenProvider {

    override fun NavGraphBuilder.register(navController: NavController) {
        composable<BookingRoute> { backStackEntry ->
            val route: BookingRoute = backStackEntry.toRoute()
            BookingScreen(preselectedCatId = route.preselectedCatId)
        }
    }
}
