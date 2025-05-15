package org.albert.x16_jetpack_concepts.navigation.nav_graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import org.albert.x16_jetpack_concepts.navigation.AUTH_ROUTE
import org.albert.x16_jetpack_concepts.navigation.Screen
import org.albert.x16_jetpack_concepts.navigation.screens.LoginScreen
import org.albert.x16_jetpack_concepts.navigation.screens.SignUpScreen

fun NavGraphBuilder.authNavGraph(navHostController: NavHostController) {
    navigation(
        startDestination = Screen.Login.route,
        route = AUTH_ROUTE,
    ) {
        composable(Screen.Login.route) {
            LoginScreen(navHostController)
        }
        composable(Screen.SignUp.route) {
            SignUpScreen(navHostController)
        }
    }
}