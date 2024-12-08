package andrewafony.testapp.chat

import andrewafony.testapp.domain.model.ChatMessage
import android.util.Log
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowLeft
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner
import coil3.compose.AsyncImage
import org.koin.androidx.compose.koinViewModel

@Composable
fun ComposableLifecycle(
    lifeCycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    onEvent: (LifecycleOwner, Lifecycle.Event) -> Unit
) {
    DisposableEffect(lifeCycleOwner) {
        val observer = LifecycleEventObserver { source, event ->
            onEvent(source, event)
        }
        lifeCycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifeCycleOwner.lifecycle.removeObserver(observer)
        }
    }
}

@Composable
fun ChatScreen(
    modifier: Modifier = Modifier,
    viewModel: ChatViewModel = koinViewModel(),
    navigateBack: () -> Unit,
) {

    val messages = viewModel.messages

    LaunchedEffect(Unit) { viewModel.init() }

    ComposableLifecycle { source, event ->

    }

    ChatScreenContent(
        modifier = modifier,
        user = "User",
        image = "",
        messages = messages,
        addItem = viewModel::addItem,
        navigateBack = navigateBack
    )
}

@Composable
fun ChatScreenContent(
    modifier: Modifier = Modifier,
    messages: List<ChatMessage>,
    user: String,
    image: String,
    addItem: () -> Unit,
    navigateBack: () -> Unit,
) {

    val listState = rememberLazyListState()

    LaunchedEffect(messages.size) {
        listState.animateScrollToItem(0)
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
    ) {
        ChatScreenTopBar(
            image = image,
            name = user,
            status = "Online",
            navigateBack = navigateBack
        )
        LazyColumn(
            state = listState,
            reverseLayout = true,
            modifier = Modifier
                .weight(1f)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            items(messages, key = { it.id }) { item ->
                Message(
                    modifier = Modifier
                        .animateItem(
                            fadeInSpec = tween(durationMillis = 250),
                            fadeOutSpec = tween(durationMillis = 100),
                            placementSpec = spring(
                                stiffness = Spring.StiffnessLow,
                                dampingRatio = Spring.DampingRatioLowBouncy
                            )
                        )
                        .padding(top = 24.dp),
                    message = item.message,
                    isUserMe = user == item.user
                )
            }
        }
        ChatScreenBottomBar(
            onPlusClick = addItem
        )
    }
}

@Composable
fun ChatScreenTopBar(
    modifier: Modifier = Modifier,
    image: String,
    name: String,
    status: String,
    navigateBack: () -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .drawBehind {
                drawLine(
                    color = Color.LightGray,
                    start = Offset(0f, size.height),
                    end = Offset(size.width, size.height)
                )
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Outlined.KeyboardArrowLeft,
            contentDescription = null,
            modifier = Modifier
                .clip(CircleShape)
                .clickable { navigateBack() }
                .padding(12.dp)
        )
        AsyncImage(
            model = image,
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(top = 8.dp, bottom = 8.dp, end = 8.dp)
                .clip(CircleShape)
                .size(56.dp)
        )
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = name,
                style = MaterialTheme.typography.bodyLarge,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
            Spacer(modifier = Modifier.size(4.dp))
            Text(
                text = status,
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
        }

        Icon(
            imageVector = andrewafony.testapp.designsystem.icons.VideoCamera,
            contentDescription = null,
            modifier = Modifier
                .clip(CircleShape)
                .clickable { }
                .padding(12.dp)
        )
        Icon(
            imageVector = andrewafony.testapp.designsystem.icons.IconCall,
            contentDescription = null,
            modifier = Modifier
                .clip(CircleShape)
                .clickable { }
                .padding(12.dp)
        )

    }
}

@Composable
fun ChatScreenBottomBar(
    modifier: Modifier = Modifier,
    onPlusClick: () -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface)
            .imePadding()
            .drawBehind {
                drawLine(
                    color = Color.LightGray,
                    start = Offset(0f, 0f),
                    end = Offset(size.width, 0f)
                )
            },
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Outlined.Add,
            contentDescription = null,
            modifier = Modifier
                .clickable { onPlusClick() }
        )
        TextField(
            value = "text",
            onValueChange = { },
            placeholder = {
                Text(text = "New Chat", color = Color.Gray)
            },
            textStyle = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .height(70.dp)
                .padding(vertical = 12.dp)
                .clip(RoundedCornerShape(64.dp))
                .background(Color(0xFFF5F5F5)),
            singleLine = true,
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = Color.Black
            )
        )
        Icon(
            imageVector = andrewafony.testapp.designsystem.icons.IconMicrophone,
            contentDescription = null
        )
    }
}

@Preview
@Composable
private fun ChatScreenPrev() {
    andrewafony.testapp.designsystem.theme.MangoTestChatTheme {
        Surface(color = Color.White) {
            ChatScreenContent(
                user = "Andrew Afanasiev",
                image = "",
                messages = listOf(
                    ChatMessage(
                        message = "dskfmg dfkgm dfsmg; dmfg skfdmg",
                        user = "Andrew Afanasiev",
                        timestamp = "",
                        id = 1
                    ),
                    ChatMessage(
                        message = "dskfmg dfkgm dfsmg; dmfg skfdmg",
                        user = "User",
                        timestamp = "",
                        id = 2
                    ),
                    ChatMessage(
                        message = "dskfmg dfkgm dfsmg; dmfg skfdmg",
                        user = "User",
                        timestamp = "",
                        id = 3
                    ),
                    ChatMessage(
                        message = "dskfmg dfkgm dfsmg; dmfg skfdmg",
                        user = "Andrew Afanasiev",
                        timestamp = "",
                        id = 4
                    ),
                    ChatMessage(
                        message = "Test 1",
                        user = "Andrew Afanasiev",
                        timestamp = "",
                        id = 5
                    )
                ),
                navigateBack = {},
                addItem = {}
            )
        }
    }
}