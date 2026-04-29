package ru.kvartalovea.catscafe

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Pets
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import ru.kvartalovea.catscafe.core.navigation.impl.AppNavHost
import ru.kvartalovea.catscafe.feature.booking.api.BookingRoute
import ru.kvartalovea.catscafe.feature.catalog.api.CatalogRoute
import ru.kvartalovea.catscafe.feature.home.api.HomeRoute
import ru.kvartalovea.catscafe.feature.profile.api.ProfileRoute
import ru.kvartalovea.catscafe.feature.splash.api.SplashRoute
import kotlin.reflect.KClass

private data class BottomTab(
    val route: Any,
    val routeClass: KClass<*>,
    val label: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
)

private val BOTTOM_TABS = listOf(
    BottomTab(
        route = HomeRoute,
        routeClass = HomeRoute::class,
        label = "Главная",
        selectedIcon = Icons.Filled.Home,
        unselectedIcon = Icons.Outlined.Home,
    ),
    BottomTab(
        route = CatalogRoute,
        routeClass = CatalogRoute::class,
        label = "Каталог",
        selectedIcon = Icons.Filled.Pets,
        unselectedIcon = Icons.Outlined.Pets,
    ),
    BottomTab(
        route = BookingRoute(),
        routeClass = BookingRoute::class,
        label = "Бронь",
        selectedIcon = Icons.Filled.CalendarMonth,
        unselectedIcon = Icons.Outlined.CalendarMonth,
    ),
    BottomTab(
        route = ProfileRoute,
        routeClass = ProfileRoute::class,
        label = "Профиль",
        selectedIcon = Icons.Filled.Person,
        unselectedIcon = Icons.Outlined.Person,
    ),
)

@Composable
fun MainScaffold() {
    val navController = rememberNavController()
    val currentBackStack by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStack?.destination

    val showBottomBar = BOTTOM_TABS.any { tab ->
        currentDestination?.hasRoute(tab.routeClass) == true
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if (showBottomBar) {
                AppBottomNavigationBar(
                    currentDestination = currentDestination,
                    onTabSelected = { tab ->
                        navController.navigate(tab.route) {
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                )
            }
        },
    ) { paddingValues ->
        AppNavHost(
            startDestination = SplashRoute,
            navController = navController,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .consumeWindowInsets(paddingValues),
        )
    }
}

@Composable
private fun AppBottomNavigationBar(
    currentDestination: NavDestination?,
    onTabSelected: (BottomTab) -> Unit,
) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surface,
        windowInsets = WindowInsets(0),
    ) {
        BOTTOM_TABS.forEach { tab ->
            val isSelected = currentDestination?.hasRoute(tab.routeClass) == true
            NavigationBarItem(
                selected = isSelected,
                onClick = { onTabSelected(tab) },
                icon = {
                    Icon(
                        imageVector = if (isSelected) tab.selectedIcon else tab.unselectedIcon,
                        contentDescription = tab.label,
                    )
                },
                label = { Text(text = tab.label) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    selectedTextColor = MaterialTheme.colorScheme.primary,
                    indicatorColor = MaterialTheme.colorScheme.primaryContainer,
                    unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                ),
            )
        }
    }
}
