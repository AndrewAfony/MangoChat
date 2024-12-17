package andrewafony.testapp.home.stories

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun NewStoryItem(
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Box(
            modifier = modifier
                .size(64.dp)
                .clip(CircleShape)
                .drawBehind {
                    val pathEffect = PathEffect.dashPathEffect(floatArrayOf(35f, 35f), 0f)
                    val stroke = Stroke(width = 2f, pathEffect = pathEffect)
                    drawOval(Color.LightGray, style = stroke)
                },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                Icons.Default.Add,
                "",
                modifier = Modifier.size(36.dp)
            )
        }
        Text(
            text = "Add story",
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Preview
@Composable
private fun NewStoryItemPrev() {
    NewStoryItem()
}