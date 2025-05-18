package org.albert.x20_splashscreen.screens

sealed class Screen(val route: String) {
    data object Home: Screen("home_route")
    data object Splash: Screen("splash_route")
}
