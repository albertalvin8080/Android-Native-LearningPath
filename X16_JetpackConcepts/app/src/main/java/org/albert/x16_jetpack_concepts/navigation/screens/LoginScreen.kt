package org.albert.x16_jetpack_concepts.navigation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import org.albert.x16_jetpack_concepts.navigation.Screen

@Composable
fun LoginScreen(navHostController: NavHostController) {
    Box(
        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center,
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Login",
                fontSize = MaterialTheme.typography.headlineLarge.fontSize,
                color = MaterialTheme.colorScheme.secondary,
                modifier = Modifier.clickable {
                    navHostController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Home.route) {
                            inclusive = true
                        }
                    }
                })
            Button(onClick = {
                navHostController.navigate(Screen.SignUp.route)
            }) {
                Text(
                    "Sign Up",
                    fontSize = MaterialTheme.typography.headlineLarge.fontSize,
                    color = Color.Black,
                )
            }
        }
    }
}