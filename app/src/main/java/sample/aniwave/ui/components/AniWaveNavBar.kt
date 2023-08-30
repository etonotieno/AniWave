package sample.aniwave.ui.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import sample.aniwave.ui.navigation.MainDestination

private fun NavDestination?.isMainDestination(destination: MainDestination) =
    this?.hierarchy?.any {
        it.route?.contains(destination.name, true) ?: false
    } ?: false

@Composable
fun AniWaveNavBar(
    navigationRoutes: List<MainDestination>,
    onNavigationSelected: (MainDestination) -> Unit,
    currentDestination: NavDestination?,
    modifier: Modifier = Modifier,
) {
    NavigationBar(modifier = modifier.testTag("AniWaveBottomBar")) {
        navigationRoutes.forEach { route ->
            val selected = currentDestination.isMainDestination(route)
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = if (selected) route.selectedIcon else route.unselectedIcon,
                        contentDescription = null,
                    )
                },
                label = {
                    Text(text = stringResource(route.titleTextId))
                },
                selected = selected,
                onClick = { onNavigationSelected(route) },
            )
        }
    }
}
