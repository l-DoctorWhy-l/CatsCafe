package ru.kvartalovea.catscafe.feature.profile.impl

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ru.kvartalovea.catscafe.core.navigation.api.ScreenProvider
import ru.kvartalovea.catscafe.feature.profile.api.ProfileRoute
import ru.kvartalovea.catscafe.feature.profile.impl.presentation.ProfileScreen

internal class ProfileScreenProvider : ScreenProvider {

    override fun NavGraphBuilder.register(navController: NavController) {
        composable<ProfileRoute> {
            ProfileScreen()
        }
    }
}
