package sample.aniwave.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import kotlinx.coroutines.CoroutineScope
import sample.aniwave.navigation.AppNavigation
import sample.aniwave.navigation.MainDestination
import sample.aniwave.navigation.navigateToHome
import sample.aniwave.navigation.navigateToSearch

class AniWaveAppState(
    val navController: NavHostController,
    val coroutineScope: CoroutineScope,
) {
    val currentMainDestination: MainDestination?
        @Composable get() = when (currentDestination?.route) {
            AppNavigation.Home.route -> MainDestination.HOME
            AppNavigation.Search.route -> MainDestination.SEARCH
            else -> null
        }

    val currentDestination: NavDestination?
        @Composable get() = navController.currentBackStackEntryAsState().value?.destination

    fun navigateToMainDestination(destination: MainDestination) {
        val navOptions = navOptions {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }

        when (destination) {
            MainDestination.HOME -> navController.navigateToHome(navOptions)
            MainDestination.SEARCH -> navController.navigateToSearch(navOptions)
        }
    }
}

@Composable
fun rememberAniWaveAppState(
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navController: NavHostController = rememberNavController(),
): AniWaveAppState {
    return remember(navController, coroutineScope) {
        AniWaveAppState(navController, coroutineScope)
    }
}
