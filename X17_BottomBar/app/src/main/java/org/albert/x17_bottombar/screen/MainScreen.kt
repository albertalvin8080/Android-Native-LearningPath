package org.albert.x17_bottombar.screen

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import org.albert.x17_bottombar.navigation.BottomBarScreen
import org.albert.x17_bottombar.navigation.BottomNavGraph

@Composable
fun MainScreen() {
    val navHostController = rememberNavController()
    Scaffold(
        bottomBar = { BottomBar(navHostController) }
    ) { innerPadding ->
        BottomNavGraph(navHostController, innerPadding)
    }
}

@Composable
fun BottomBar(navHostController: NavHostController) {
    val screens = listOf(
        BottomBarScreen.Home,
        BottomBarScreen.Profile,
        BottomBarScreen.Settings,
    )
    val backStackEntry by navHostController.currentBackStackEntryAsState()
    val destination = backStackEntry?.destination

    NavigationBar {
        screens.forEach { screen ->
            AddItem(screen, destination, navHostController)
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomBarScreen,
    destination: NavDestination?,
    navHostController: NavHostController,
) {
    NavigationBarItem(
        label = { Text(screen.title) },
        icon = {
            Icon(imageVector = screen.icon, contentDescription = "Nav-Item")
        },
        selected = destination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        onClick = {
            navHostController.navigate(screen.route) {
//                popUpTo(navHostController.graph.findStartDestination().id)
                popUpTo(navHostController.graph.startDestinationId)
                launchSingleTop = true
            }
        }
    )
}