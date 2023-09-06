/*
 * Copyright 2023 Eton Otieno
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package sample.aniwave.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import sample.aniwave.navigation.AppNavigation
import sample.aniwave.navigation.MainDestination
import sample.aniwave.navigation.navigateToHome
import sample.aniwave.navigation.navigateToSearch

class AniWaveAppState(
    val navController: NavHostController,
) {
    val mainDestinations: List<MainDestination> = MainDestination.values().toList()

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
    navController: NavHostController = rememberNavController(),
): AniWaveAppState {
    return remember(navController) {
        AniWaveAppState(navController)
    }
}
