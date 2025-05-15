package org.albert.x18_topsearchbar.appbar

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SearchAppBar(
    text: String,
    onTextChange: (String) -> Unit,
    onCloseClicked: () -> Unit,
    onSearchClicked: (String) -> Unit,
) {
    Surface(
        modifier = Modifier
            .statusBarsPadding()
            .fillMaxWidth()
            .height(56.dp),
        color = MaterialTheme.colorScheme.surface,
        tonalElevation = 3.dp
    ) {
        TextField(
            value = text,
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text(text = "Search", color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f))
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
            ),
            textStyle = TextStyle(color = MaterialTheme.colorScheme.onSurface),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions( // This one handles "enter"
                onSearch = { onSearchClicked(text) },
            ),
            onValueChange = { onTextChange(text) },
            leadingIcon = {
                IconButton(onClick = { onSearchClicked(text) }) {
                    Icon(
                        tint = MaterialTheme.colorScheme.onSurface,
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search Icon"
                    )
                }
            },
            trailingIcon = {
                IconButton(onClick = {
                    if (text.isNotEmpty()) {
                        onTextChange("")
                    } else {
                        onCloseClicked()
                    }
                }) {
                    Icon(
                        tint = MaterialTheme.colorScheme.onSurface,
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close",
                    )
                }
            }
        )
    }
}

@Composable
@Preview(showBackground = true)
fun SearchAppBarPreview() {
    SearchAppBar (
        text = "Detestatio Sacrorum",
        onTextChange = {},
        onCloseClicked = {},
        onSearchClicked = {},
    )
}