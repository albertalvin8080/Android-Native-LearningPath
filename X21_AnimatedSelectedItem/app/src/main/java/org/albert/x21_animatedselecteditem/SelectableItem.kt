package org.albert.x21_animatedselecteditem

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import org.albert.x21_animatedselecteditem.ui.theme.Purple80

@Composable
fun SelectableItem(
    selected: Boolean = false,
    title: String = "Detestatio Sacrorum",
    onClick: () -> Unit,
    content: String? = null,
) {
    val borderSize: Dp = 4.dp
    val color = if (selected) Purple80 else Purple80.copy(alpha = 0.2f)
    val contentColor = if (selected) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)

    val scaleColumn = remember { Animatable(1f) }
    val scaleIcon = remember { Animatable(1f) }

    LaunchedEffect(selected) {
        if (!selected) return@LaunchedEffect
        launch {
            scaleColumn.animateTo(
                targetValue = 0.9f,
                animationSpec = tween(
                )
            )
            scaleColumn.animateTo(
                targetValue = 1f,
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioLowBouncy,
                    stiffness = Spring.StiffnessLow,
                )
            )
        }
        launch {
            scaleIcon.animateTo(
                targetValue = 0.3f,
                animationSpec = tween(
                )
            )
            scaleIcon.animateTo(
                targetValue = 1f,
                animationSpec = spring(
                    stiffness = Spring.StiffnessLow,
                    dampingRatio = Spring.DampingRatioLowBouncy,
                )
            )
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .scale(scaleColumn.value)
            .clip(RoundedCornerShape(12.dp))
            .clickable { onClick() }
            .border(borderSize, color, RoundedCornerShape(12.dp))
            .padding(12.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                color = color,
                fontSize = MaterialTheme.typography.labelLarge.fontSize.times(1.1),
                modifier = Modifier.weight(8f),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            IconButton(onClick = {onClick()}) {
                Icon(
                    imageVector = if(selected) Icons.Default.CheckCircle else Icons.Outlined.AddCircle,
                    contentDescription = "Selection icon",
                    tint = color,
                    modifier = Modifier.weight(2f).scale(scaleIcon.value),
                )
            }
        }
        if (content != null) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = content,
                color = contentColor,
                fontSize = MaterialTheme.typography.labelLarge.fontSize.times(1),
            )
        }
    }
}