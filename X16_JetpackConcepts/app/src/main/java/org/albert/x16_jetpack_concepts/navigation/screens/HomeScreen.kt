package org.albert.x16_jetpack_concepts.navigation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import org.albert.x16_jetpack_concepts.navigation.AUTH_ROUTE
import org.albert.x16_jetpack_concepts.navigation.Screen

@Composable
fun HomeScreen(navHostController: NavHostController) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                modifier = Modifier.clickable {
                    navHostController.navigate(Screen.Details.withIdAndName(5, "Detestatio"))
                },
                text = "Home",
                color = MaterialTheme.colorScheme.primary,
                fontSize = MaterialTheme.typography.headlineLarge.fontSize,
            )
            Text(
                modifier = Modifier
                    .padding(top = 40.dp)
                    .clickable {
                        navHostController.navigate(AUTH_ROUTE)
                    },
                text = "Login",
                color = MaterialTheme.colorScheme.secondary,
                fontSize = MaterialTheme.typography.labelLarge.fontSize,
            )
        }
    }
}