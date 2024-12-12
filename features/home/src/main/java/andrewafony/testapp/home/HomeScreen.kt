package andrewafony.testapp.home

import andrewafony.testapp.designsystem.SetWindowSoftInputMode
import andrewafony.testapp.domain.model.Chat
import andrewafony.testapp.home.stories.HomeScreenStories
import android.view.WindowManager
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = koinViewModel(),
    navigateToChat: (String) -> Unit
) {

    SetWindowSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)

    val chats by viewModel.chats.collectAsStateWithLifecycle()

    HomeScreenContent(
        modifier = modifier,
        chats = chats,
        navigateToChat = navigateToChat
    )
}

@Composable
fun HomeScreenContent(
    modifier: Modifier = Modifier,
    chats: List<Chat>,
    navigateToChat: (String) -> Unit
) {

    val chatsListState = rememberLazyListState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
            .padding(bottom = 80.dp),
    ) {
        HomeScreenTitle()
        HomeScreenStories()
        HomeScreenChatsTitle()
        HomeScreenChats(
            chatsListState = chatsListState,
            chatList = chats,
            navigateToChat = navigateToChat
        )
    }
}

@Composable
fun HomeScreenTitle(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(andrewafony.testapp.designsystem.R.string.app_name),
            color = Color.Black,
            style = MaterialTheme.typography.titleLarge
        )
    }
}

@Composable
fun HomeScreenChatsTitle(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Chats",
            style = MaterialTheme.typography.bodyLarge
        )
        Icon(
            imageVector = andrewafony.testapp.designsystem.icons.More_horiz,
            "",
            modifier = Modifier
                .clickable {  }
        )
    }
}



@Preview
@Composable
private fun HomeScreenPrev() {
    andrewafony.testapp.designsystem.theme.MangoTestChatTheme {
        HomeScreenContent(
            chats = emptyList(),
            navigateToChat = {}
        )
    }
}