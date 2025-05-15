package org.albert.x16_jetpack_concepts.navigation.nav_graph

import android.util.Log
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import org.albert.x16_jetpack_concepts.navigation.DETAILS_OPTIONAL_ARG_KEY
import org.albert.x16_jetpack_concepts.navigation.DETAILS_REQUIRED_ARG_KEY
import org.albert.x16_jetpack_concepts.navigation.HOME_ROUTE
import org.albert.x16_jetpack_concepts.navigation.Screen
import org.albert.x16_jetpack_concepts.navigation.screens.DetailsScreen
import org.albert.x16_jetpack_concepts.navigation.screens.HomeScreen

fun NavGraphBuilder.homeNavGraph(navHostController: NavHostController) {
    navigation(
        startDestination = Screen.Home.route,
        route = HOME_ROUTE,
    ) {
        composable(Screen.Home.route) {
            HomeScreen(navHostController)
        }
        composable(
            route = Screen.Details.route,
            arguments = listOf(
                navArgument(DETAILS_REQUIRED_ARG_KEY) {
                    type = NavType.IntType
                },
                navArgument(DETAILS_OPTIONAL_ARG_KEY) {
                    type = NavType.StringType
                    defaultValue = "default" // necessary when the arg is optional
//                    nullable = true // could use this instead, but better not
                },
            )
        ) {
//            Log.d("DETAILS", it.arguments?.getInt(DETAILS_REQUIRED_ARG_KEY).toString())
//            Log.d("DETAILS", it.arguments?.getString(DETAILS_OPTIONAL_ARG_KEY).toString())
            DetailsScreen(navHostController)
        }
    }
}