package org.albert.x17_bottombar.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.albert.x17_bottombar.screen.HomeScreen
import org.albert.x17_bottombar.screen.ProfileScreen
import org.albert.x17_bottombar.screen.SettingsScreen

@Composable
fun BottomNavGraph(
    navHostController: NavHostController,
    paddingValues: PaddingValues,
) {
    NavHost(
        navController = navHostController,
        startDestination = BottomBarScreen.Home.route,
        modifier = Modifier.padding(paddingValues)
    ) {
        composable(BottomBarScreen.Home.route) {
            HomeScreen(navHostController)
        }
        composable(BottomBarScreen.Profile.route) {
            ProfileScreen(navHostController)
        }
        composable (BottomBarScreen.Settings.route) {
            SettingsScreen(navHostController)
        }
    }
}