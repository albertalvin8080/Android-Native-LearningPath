package org.albert.x21_animatedselecteditem

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.albert.x21_animatedselecteditem.ui.theme.X21_AnimatedSelectedItemTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            X21_AnimatedSelectedItemTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .padding(innerPadding)
                            .padding(60.dp)
                            .fillMaxSize()
                    ) {
                        var stateA by remember{ mutableStateOf(false) }
                        var stateB by remember{ mutableStateOf(false) }

                        SelectableItem(selected = stateA, onClick = { stateA = !stateA})
                        Spacer(Modifier.height(12.dp))
                        SelectableItem(selected = stateB, onClick = { stateB = !stateB}, content = "Summa blasphemia, lorem ipsum dolor amet scir sciato. Exemplaris excomunicatio. Wouldst thou truly lordship sanction in one so bereft of light?")
                    }
                }
            }
        }
    }
}
