package org.albert.x20_splashscreen

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import org.albert.x20_splashscreen.navigation.SetupNavGraph

@Composable
fun MainScreen(innerPadding: PaddingValues) {
    val navHostController = rememberNavController()
    SetupNavGraph(navHostController)
}