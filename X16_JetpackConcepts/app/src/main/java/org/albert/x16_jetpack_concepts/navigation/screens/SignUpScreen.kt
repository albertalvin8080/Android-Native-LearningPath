package org.albert.x16_jetpack_concepts.navigation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import org.albert.x16_jetpack_concepts.navigation.Screen

@Composable
fun SignUpScreen(navHostController: NavHostController) {
    Box(
        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center,
    ) {
        Text(text = "Sign Up",
            fontSize = MaterialTheme.typography.headlineLarge.fontSize,
            color = Color.Red,
            modifier = Modifier.clickable {
                navHostController.navigate(Screen.Home.route) {
                    popUpTo(Screen.Home.route) {
                        inclusive = true
                    }
                }
            }
        )
    }
}