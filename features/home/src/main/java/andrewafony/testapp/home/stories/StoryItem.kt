package andrewafony.testapp.home.stories

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage

@Composable
fun StoryItem(
    modifier: Modifier = Modifier,
    image: String,
    name: String,
    onClick: () -> Unit
) {

    Column(
        modifier = modifier.padding(end = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = image,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(CircleShape)
                .size(64.dp)
        )
        Spacer(modifier = Modifier.size(12.dp))
        Text(
            text = name,
            style = MaterialTheme.typography.bodyMedium
        )
    }


}

@Preview
@Composable
private fun StoryItemPrev() {
//    StoryItem(R.drawable.test_image, "Andrew") { }
}