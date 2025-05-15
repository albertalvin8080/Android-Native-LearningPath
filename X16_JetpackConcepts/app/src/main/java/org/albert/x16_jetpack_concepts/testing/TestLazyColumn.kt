package org.albert.x16_jetpack_concepts.testing

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import org.albert.x16_jetpack_concepts.data.Author
import org.albert.x16_jetpack_concepts.data.AuthorRepository

@Composable
fun TestLazyColumn() {
    LazyColumn(
        contentPadding = PaddingValues(12.dp), verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        val authors = AuthorRepository.getAll();
        itemsIndexed(authors) { idx, author ->
            Log.d("LAZY", idx.toString())
            AuthorContainer(author)
        }
    }
}

@Composable
fun AuthorContainer(author: Author) {
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .background(MaterialTheme.colorScheme.primary)
            .padding(12.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Text(author.id.toString())
        Text(author.name)
    }
}