package org.albert.x20_splashscreen.screens

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import kotlinx.coroutines.delay
import org.albert.x20_splashscreen.ui.theme.Purple80

const val duration = 2000

@Composable
fun SplashScreen(navHostController: NavHostController) {
    var startAnimation by remember { mutableStateOf(false) }
    val alpha by animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(
            durationMillis = duration,
        )
    )

    Content(alpha)

    LaunchedEffect(Unit) {
        startAnimation = true
        delay(duration + 500L)
        navHostController.popBackStack()
        navHostController.navigate(Screen.Home.route)
    }
}

@Composable
private fun Content(alpha: Float) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(
                if (isSystemInDarkTheme()) Color.Black else Purple80
            )
            .alpha(alpha)
    ) {
        Icon(
            imageVector = Icons.Default.Email,
            contentDescription = "mail",
            tint = Color.White,
            modifier = Modifier.size(120.dp)
        )
    }
}