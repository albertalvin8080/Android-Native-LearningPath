package org.albert.x16_jetpack_concepts.navigation.nav_graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import org.albert.x16_jetpack_concepts.navigation.HOME_ROUTE
import org.albert.x16_jetpack_concepts.navigation.ROOT_ROUTE
import org.albert.x16_jetpack_concepts.navigation.Screen

@Composable
fun SetUpNavHost(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = HOME_ROUTE,
        route = ROOT_ROUTE,
    ) {
        homeNavGraph(navHostController)
        authNavGraph(navHostController)
    }
}