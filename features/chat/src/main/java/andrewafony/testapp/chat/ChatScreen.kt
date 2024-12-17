package andrewafony.testapp.chat

import andrewafony.testapp.domain.model.ChatMessage
import andrewafony.testapp.domain.model.User
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.outlined.Send
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import org.koin.androidx.compose.koinViewModel

@Composable
fun ChatScreen(
    modifier: Modifier = Modifier,
    viewModel: ChatViewModel = koinViewModel(),
    navigateBack: () -> Unit,
) {

    val messagesState by viewModel.messages.collectAsStateWithLifecycle()
    val user by viewModel.user.collectAsStateWithLifecycle()

    ChatScreenContent(
        modifier = modifier,
        messagesState = messagesState,
        sendMessage = viewModel::sendMessage,
        user = user,
        navigateBack = navigateBack
    )
}

@Composable
fun ChatScreenContent(
    modifier: Modifier = Modifier,
    messagesState: MessagesState,
    user: User,
    sendMessage: (String) -> Unit,
    navigateBack: () -> Unit,
) {

    var messageText by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
    ) {
        ChatScreenTopBar(
            user = user,
            navigateBack = navigateBack
        )
        ChatMessages(
            modifier = Modifier
                .weight(1f),
            state = messagesState,
            userName = user.name
        )
        ChatScreenBottomBar(
            messageText = messageText,
            onMessageInput = { messageText = it },
            onMessageSend = {
                sendMessage(it)
                messageText = ""
            },
            onPlusClick = {}
        )
    }
}

@Composable
fun ChatScreenTopBar(
    modifier: Modifier = Modifier,
    user: User,
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
            model = user.image,
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(top = 8.dp, bottom = 8.dp, end = 8.dp)
                .clip(CircleShape)
                .size(56.dp)
        )
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = user.name,
                style = MaterialTheme.typography.bodyLarge,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
            Text(
                text = user.status,
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
fun ChatMessages(
    modifier: Modifier = Modifier,
    userName: String,
    state: MessagesState,
) {

    val listState = rememberLazyListState()

    when (state) {
        is MessagesState.Loading -> {
            Box(modifier = modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        is MessagesState.Success -> {

            LaunchedEffect(state.messages.size) {
                listState.animateScrollToItem(0)
            }

            if (state.messages.isEmpty()) {
                Box(modifier = modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    Text(
                        text = "Сообщений пока нет...",
                        fontStyle = FontStyle.Italic,
                        color = Color.Gray
                    )
                }
            } else {
                LazyColumn(
                    modifier = modifier
                        .padding(16.dp),
                    state = listState,
                    reverseLayout = true,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Bottom
                ) {
                    items(state.messages, key = { it.id }) { item ->
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
                            isUserMe = userName == item.user
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ChatScreenBottomBar(
    modifier: Modifier = Modifier,
    messageText: String,
    onMessageInput: (String) -> Unit,
    onPlusClick: () -> Unit,
    onMessageSend: (String) -> Unit,
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
            value = messageText,
            onValueChange = onMessageInput,
            placeholder = {
                Text(
                    text = "Сообщение",
                    color = Color.Gray,
                    style = MaterialTheme.typography.bodySmall
                )
            },
            textStyle = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .padding(vertical = 12.dp)
                .clip(RoundedCornerShape(24.dp))
                .background(Color(0xFFF5F5F5)),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = Color.Black
            ),
            keyboardActions = KeyboardActions(
                onSend = { onMessageSend(messageText) }
            ),
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Sentences,
                imeAction = ImeAction.Send
            )
        )

        AnimatedContent(
            targetState = messageText.isBlank(),
            transitionSpec = {
                scaleIn() + fadeIn() togetherWith scaleOut() + fadeOut()
            }
        ) { isTextBlank ->
            if (isTextBlank) {
                Icon(
                    imageVector = andrewafony.testapp.designsystem.icons.IconMicrophone,
                    contentDescription = null
                )
            } else {
                Icon(
                    modifier = Modifier
                        .clip(CircleShape)
                        .clickable { onMessageSend(messageText) },
                    imageVector = Icons.AutoMirrored.Outlined.Send,
                    contentDescription = null
                )
            }

        }
    }
}

@Preview
@Composable
private fun ChatScreenPrev() {
    andrewafony.testapp.designsystem.theme.MangoTestChatTheme {
        Surface(color = Color.White) {
            ChatScreenContent(
                messagesState = MessagesState.Success(
                    messages = listOf(
                        ChatMessage(
                            id = 1,
                            message = "Message content",
                            user = "user",
                            timestamp = ""
                        )
                    ),
                ),
                user = User.empty(),
                navigateBack = {},
                sendMessage = {}
            )
        }
    }
}

@Preview
@Composable
private fun ChatScreenPrevEmptyList() {
    andrewafony.testapp.designsystem.theme.MangoTestChatTheme {
        Surface(color = Color.White) {
            ChatScreenContent(
                messagesState = MessagesState.Success(messages = emptyList()),
                user = User.empty(),
                navigateBack = {},
                sendMessage = {}
            )
        }
    }
}