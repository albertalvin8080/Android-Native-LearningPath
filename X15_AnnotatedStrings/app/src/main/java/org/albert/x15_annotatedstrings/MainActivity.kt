package org.albert.x15_annotatedstrings

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.albert.x15_annotatedstrings.ui.theme.X15_AnnotatedStringsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            X15_AnnotatedStringsTheme {
                Scaffold (
                    content = { paddingValues ->
                        // Apply padding from the Scaffold to prevent overlap
                        Box(modifier = Modifier.padding(paddingValues)) {
                            ExpandableCard(
                                title = "Detestatio Sacrorum",
                                content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididut laborum. SSsssssssssssss ssssssssssss sssssss sssssss ssssssssss ssssssssss sssssssssssss ssssssssssssssssss"
                            )
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun CustomText(myText: String) {
    var cIdx = 0;
    val colors = listOf(
        MaterialTheme.colorScheme.primary,
        MaterialTheme.colorScheme.secondary,
        MaterialTheme.colorScheme.error,
        MaterialTheme.colorScheme.scrim,
        Color.Yellow,
    )
    Text(
        buildAnnotatedString {
            withStyle(style = ParagraphStyle()) {
                for (char in myText) {
                    withStyle(SpanStyle(color = colors[cIdx % colors.size])) {
                        append(char)
                    }
                    ++cIdx
                }
            }
        }, modifier = Modifier.width(200.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    X15_AnnotatedStringsTheme {
        Column {
            CustomText("Detestatio Sacrorum")
        }
    }
}