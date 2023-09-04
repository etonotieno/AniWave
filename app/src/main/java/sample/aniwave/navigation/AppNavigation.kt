package sample.aniwave.navigation

import androidx.navigation.NavController
import androidx.navigation.NavOptions

fun NavController.navigateToHome(navOptions: NavOptions? = null) {
    this.navigate(AppNavigation.Home.route, navOptions)
}

fun NavController.navigateToUpload(navOptions: NavOptions? = null) {
    this.navigate(AppNavigation.Upload.route, navOptions)
}

enum class AppNavigation(val route: String) {
    Home("home"),
    Upload("upload"),
}
