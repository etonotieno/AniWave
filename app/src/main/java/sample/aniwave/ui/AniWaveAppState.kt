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
import sample.aniwave.ui.navigation.AppNavigation
import sample.aniwave.ui.navigation.MainDestination
import sample.aniwave.ui.navigation.navigateToHome
import sample.aniwave.ui.navigation.navigateToUpload

class AniWaveAppState(
    val navController: NavHostController,
    val coroutineScope: CoroutineScope,
) {
    val currentMainDestination: MainDestination?
        @Composable get() = when (currentDestination?.route) {
            AppNavigation.Home.route -> MainDestination.HOME
            AppNavigation.Upload.route -> MainDestination.UPLOAD
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
            MainDestination.UPLOAD -> navController.navigateToUpload(navOptions)
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
