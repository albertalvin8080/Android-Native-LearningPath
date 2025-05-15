package org.albert.x17_bottombar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import org.albert.x17_bottombar.screen.MainScreen
import org.albert.x17_bottombar.ui.theme.X17_BottomBarTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            X17_BottomBarTheme {
                MainScreen()
            }
        }
    }
}
