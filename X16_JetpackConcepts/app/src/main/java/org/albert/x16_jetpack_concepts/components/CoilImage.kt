package org.albert.x16_jetpack_concepts.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import coil.transform.RoundedCornersTransformation
import coil.transform.Transformation
import org.albert.x16_jetpack_concepts.R

@Composable
fun CoilImage() {
    Box(
        modifier = Modifier
            .width(150.dp)
            .height(150.dp),
        contentAlignment = Alignment.Center
    ) {
        val url =
            "https://static1.personality-database.com/profile_images/00111b1bfa944f60a85b09c446826a05.png"
        val painter = rememberAsyncImagePainter(
            model = ImageRequest.Builder(LocalContext.current)
                .data(url)
//                .placeholder(R.drawable.baseline_hourglass_empty_24)
                .error(R.drawable.ic_launcher_foreground)
                .transformations(
                    transformations = listOf(
//                        CircleCropTransformation(),
                        RoundedCornersTransformation(20f),
                    )
                )
                .build()
        )
        val painterState = painter.state;
        Image(painter, contentDescription = "Philomela", modifier = Modifier.fillMaxSize())
        if (painterState is AsyncImagePainter.State.Loading)
            CircularProgressIndicator()
    }
}