package sample.aniwave.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import sample.aniwave.ui.AniWaveAppState
import sample.aniwave.ui.home.HomeScreen
import sample.aniwave.ui.upload.UploadPhotoScreen

@Composable
fun AniWaveNavHost(
    appState: AniWaveAppState,
    startDestination: String,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = appState.navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        composable(AppNavigation.Home.route) {
            HomeScreen()
        }
        composable(AppNavigation.Upload.route) {
            UploadPhotoScreen()
        }
    }
}
