package org.albert.x16_jetpack_concepts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.albert.x16_jetpack_concepts.components.CoilImage
import org.albert.x16_jetpack_concepts.components.GoogleButton
import org.albert.x16_jetpack_concepts.components.PasswordInput
import org.albert.x16_jetpack_concepts.components.TextFieldTest
import org.albert.x16_jetpack_concepts.testing.TestLazyColumn
import org.albert.x16_jetpack_concepts.ui.theme.X16_JetpackConceptsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            X16_JetpackConceptsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
//                            TextFieldTest()
//                            GoogleButton()
//                            CoilImage()
//                            PasswordInput()
                            TestLazyColumn()
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!", modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    X16_JetpackConceptsTheme {
        Greeting("Android")
    }
}