package sample.aniwave

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import sample.aniwave.ui.AniWaveAppState
import sample.aniwave.ui.components.AniWaveNavBar
import sample.aniwave.ui.navigation.AniWaveNavHost
import sample.aniwave.ui.navigation.AppNavigation
import sample.aniwave.ui.navigation.MainDestination
import sample.aniwave.ui.rememberAniWaveAppState

@Composable
fun AniWaveApp(
    modifier: Modifier = Modifier,
    appState: AniWaveAppState = rememberAniWaveAppState(),
) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background,
    ) {
        Scaffold(
            bottomBar = {
                AniWaveNavBar(
                    navigationRoutes = MainDestination.values().asList(),
                    onNavigationSelected = appState::navigateToMainDestination,
                    currentDestination = appState.currentDestination,
                )
            },
        ) { padding ->
            Column(modifier = Modifier.padding(padding)) {
                AniWaveNavHost(
                    appState = appState,
                    startDestination = AppNavigation.Home.route,
                )
            }
        }
    }
}
