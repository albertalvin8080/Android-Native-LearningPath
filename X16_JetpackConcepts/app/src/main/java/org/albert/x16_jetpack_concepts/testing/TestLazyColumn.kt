package org.albert.x16_jetpack_concepts.testing

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.albert.x16_jetpack_concepts.data.Author
import org.albert.x16_jetpack_concepts.data.AuthorRepository

@Composable
fun TestLazyColumn() {
    LazyColumn(
        contentPadding = PaddingValues(12.dp), verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        val authors = AuthorRepository.getAll();
        items(authors) { author ->
            AuthorContainer(author)
        }
    }
}

@Composable
fun AuthorContainer(author: Author) {
    Row(
        modifier = Modifier
            .padding(12.dp)
            .background(MaterialTheme.colorScheme.secondary),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(author.id.toString())
        Text(author.name)
    }
}