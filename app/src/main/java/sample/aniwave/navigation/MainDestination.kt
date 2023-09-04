package sample.aniwave.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Upload
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Upload
import androidx.compose.ui.graphics.vector.ImageVector
import sample.aniwave.R

enum class MainDestination(
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val titleTextId: Int,
    val displayTextId: Int,
) {
    HOME(
        selectedIcon = Icons.Default.Home,
        unselectedIcon = Icons.Outlined.Home,
        titleTextId = R.string.home,
        displayTextId = R.string.top_anime,
    ),
    UPLOAD(
        selectedIcon = Icons.Default.Upload,
        unselectedIcon = Icons.Outlined.Upload,
        titleTextId = R.string.upload,
        displayTextId = R.string.search_anime,
    ),
}
