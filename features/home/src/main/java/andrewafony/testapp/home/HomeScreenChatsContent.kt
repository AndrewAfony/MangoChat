package andrewafony.testapp.home

import andrewafony.testapp.designsystem.theme.MangoTestChatTheme
import andrewafony.testapp.domain.model.Chat
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
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
import java.time.LocalDate
import java.time.LocalTime

@Composable
fun HomeScreenChats(
    modifier: Modifier = Modifier,
    chatList: List<Chat>,
    chatsListState: LazyListState,
    navigateToChat: (String) -> Unit,
) {
    LazyColumn(
        state = chatsListState,
        modifier = modifier
            .fillMaxSize(),
    ) {
        items(chatList, key = { it.id }) { item ->
            HomeScreenChatItem(
                image = item.image,
                name = item.name,
                message = item.lastMessage ?: "",
                newMessages = item.newMessages,
                lastMessageTime = toChatDate(item.lastMessageDate, item.lastMessageTime),
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
    lastMessageTime: String,
    newMessages: Int,
    onChatClick: (String) -> Unit,
) {

    ListItem(
        modifier = modifier
            .clickable { onChatClick(name) },
        leadingContent = {
            AsyncImage(
                model = image,
                contentDescription = null,
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
            Text(
                text = message,
                color = Color.Gray,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        trailingContent = {
            Column(
                modifier = Modifier,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = lastMessageTime,
                    color = Color.Gray,
                    style = MaterialTheme.typography.labelMedium,
                )
                Spacer(modifier = Modifier.size(4.dp))
                if (newMessages > 0) {
                    Box(
                        modifier = Modifier
                            .size(18.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.primary),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = newMessages.toString(),
                            style = MaterialTheme.typography.labelSmall,
                            color = Color.Black
                        )
                    }
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
                message = "Message text to show under name dsknfsdkf",
                newMessages = 3,
                lastMessageTime = "12:43",
                onChatClick = {}
            )
        }
    }
}

private fun toChatDate(date: LocalDate, time: LocalTime) : String {

    val currentDate = LocalDate.now()

    return if (date.dayOfYear == currentDate.dayOfYear) {
        val minutes = if (time.minute < 10) "0${time.minute}" else time.minute
        val hour = if (time.hour < 10) "0${time.hour}" else time.hour
        "$hour:$minutes"
    } else if (date.year == currentDate.year) {
        val day = if (date.dayOfMonth < 10) "0${date.dayOfMonth}" else date.dayOfMonth
        val month = if (date.monthValue < 10) "0${date.monthValue}" else date.monthValue
        "$month.$day"
    } else {
        val day = if (date.dayOfMonth < 10) "0${date.dayOfMonth}" else date.dayOfMonth
        val month = if (date.monthValue < 10) "0${date.monthValue}" else date.monthValue
        "${date.year}.$month.$day"
    }
}