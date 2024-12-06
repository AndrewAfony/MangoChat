package andrewafony.testapp.mangotestchat.feature_chats.presentation

import andrewafony.testapp.mangotestchat.R
import andrewafony.testapp.mangotestchat.theme.theme.MangoTestChatTheme
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage

@Composable
fun HomeScreenChatsContent(
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize(),
    ) {
        items(10) {
            HomeScreenChatsContent(
                image = R.drawable.test_image,
                name = "Andrew Afanasiev",
                message = "Last message",
                onChatClick = {}
            )
        }
    }
}

@Composable
fun HomeScreenChatsContent(
    modifier: Modifier = Modifier,
    image: Int,
    name: String,
    message: String,
    onChatClick : (String) -> Unit
) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onChatClick(name) },
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = image,
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(top = 8.dp, bottom = 8.dp, start = 16.dp, end = 8.dp)
                .clip(CircleShape)
                .size(56.dp)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth(0.85f)
        ) {
            Text(
                text = name,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.W500,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
            Spacer(modifier = Modifier.size(8.dp))
            Text(
                text = message,
                style = MaterialTheme.typography.bodySmall,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
        }

        Column(
            modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "12:43",
                style = MaterialTheme.typography.labelMedium,
            )
            Spacer(modifier = Modifier.size(4.dp))
            Box(
                modifier = Modifier
                    .size(18.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "2",
                    style = MaterialTheme.typography.labelSmall,
                )
            }
        }

    }
}

@Preview
@Composable
private fun HomeScreenChatsPrev() {
    MangoTestChatTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface)
        ) {
            HomeScreenChatsContent()
        }
    }
}