package org.albert.x17_bottombar.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector,
) {
    data object Home : BottomBarScreen(
        route = "home_screen",
        title = "Home",
        icon = Icons.Default.Home,
    )
    data object Profile : BottomBarScreen(
        route = "profile_screen",
        title = "Profile",
        icon = Icons.Default.Person,
    )
    data object Settings : BottomBarScreen(
        route = "settings_screen",
        title = "Settings",
        icon = Icons.Default.Settings,
    )
}