package andrewafony.testapp.home

import andrewafony.testapp.designsystem.TEST_IMAGE
import andrewafony.testapp.designsystem.theme.MangoTestChatTheme
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage

@Composable
fun HomeScreenChats(
    modifier: Modifier = Modifier,
    chatsListState: LazyListState,
    navigateToChat: (String) -> Unit
) {
    LazyColumn(
        state = chatsListState,
        modifier = modifier
            .fillMaxSize(),
    ) {
        items(40) {
            HomeScreenChatItem(
                image = TEST_IMAGE,
                name = "Andrew Afanasiev",
                message = "Last message",
                onChatClick = navigateToChat
            )
        }
    }
}

@Composable
fun HomeScreenChatItem(
    modifier: Modifier = Modifier,
    image: String,
    name: String,
    message: String,
    onChatClick: (String) -> Unit,
) {

    ListItem(
        modifier = modifier
            .clickable { onChatClick(name) },
        leadingContent = {
            AsyncImage(
                model = image,
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(CircleShape)
                    .size(56.dp)
            )
        },
        headlineContent = {
            Text(text = name, maxLines = 1, overflow = TextOverflow.Ellipsis)
        },
        supportingContent = {
            Text(text = message, maxLines = 1, overflow = TextOverflow.Ellipsis)
        },
        trailingContent = {
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
                        color = Color.Black
                    )
                }
            }
        },
    )
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
            HomeScreenChatItem(
                image = "",
                name = "Andrew",
                message = "Message 1",
                onChatClick = {}
            )
        }
    }
}