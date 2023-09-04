package sample.aniwave.navigation

import androidx.navigation.NavController
import androidx.navigation.NavOptions

fun NavController.navigateToHome(navOptions: NavOptions? = null) {
    this.navigate(AppNavigation.Home.route, navOptions)
}

fun NavController.navigateToSearch(navOptions: NavOptions? = null) {
    this.navigate(AppNavigation.Search.route, navOptions)
}

enum class AppNavigation(val route: String) {
    Home("home"),
    Search("search"),
}
