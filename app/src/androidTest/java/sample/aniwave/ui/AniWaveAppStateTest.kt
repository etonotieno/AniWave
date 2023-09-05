package sample.aniwave.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.compose.composable
import androidx.navigation.createGraph
import androidx.navigation.testing.TestNavHostController
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import sample.aniwave.navigation.AppNavigation

class AniWaveAppStateTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var state: AniWaveAppState

    @Test
    fun appState_currentDestination() = runTest {
        var currentDestination: String? = null

        composeTestRule.setContent {
            val navController = rememberTestNavController()
            state = remember(navController) {
                AniWaveAppState(
                    navController = navController,
                )
            }

            currentDestination = state.currentDestination?.route

            LaunchedEffect(Unit) {
                navController.setCurrentDestination(AppNavigation.Search.route)
            }
        }

        assertEquals(AppNavigation.Search.route, currentDestination)
    }

    @Test
    fun appState_destinations() = runTest {
        composeTestRule.setContent {
            state = rememberAniWaveAppState()
        }

        assertEquals(2, state.mainDestinations.size)
        assertTrue(state.mainDestinations[0].name.contains("home", true))
        assertTrue(state.mainDestinations[1].name.contains("search", true))
    }
}

@Composable
private fun rememberTestNavController(): TestNavHostController {
    val context = LocalContext.current
    return remember {
        TestNavHostController(context).apply {
            navigatorProvider.addNavigator(ComposeNavigator())
            graph = createGraph(startDestination = AppNavigation.Home.route) {
                composable(AppNavigation.Home.route) {}
                composable(AppNavigation.Search.route) {}
            }
        }
    }
}
