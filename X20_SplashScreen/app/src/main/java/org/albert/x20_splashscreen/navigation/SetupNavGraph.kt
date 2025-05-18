package org.albert.x20_splashscreen.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.albert.x20_splashscreen.screens.HomeScreen
import org.albert.x20_splashscreen.screens.Screen
import org.albert.x20_splashscreen.screens.SplashScreen

@Composable
fun SetupNavGraph(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.Splash.route,
    ) {
        composable(Screen.Splash.route) {
            SplashScreen(navHostController)
        }
        composable(Screen.Home.route) {
            HomeScreen(navHostController)
        }
    }
}