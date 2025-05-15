package org.albert.x16_jetpack_concepts.navigation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import org.albert.x16_jetpack_concepts.navigation.Screen

@Composable
fun DetailsScreen(navHostController: NavHostController) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier.clickable {
//                navHostController.popBackStack()
                navHostController.navigate(Screen.Home.route) {
                    popUpTo(Screen.Home.route) {
                        inclusive = true // Whether the popUpTo destination should be popped from the back stack.
                    }
                }
            },
            text = "Details",
            color = MaterialTheme.colorScheme.secondary,
            fontSize = MaterialTheme.typography.headlineLarge.fontSize,
        )
    }
}