package andrewafony.testapp.home

import andrewafony.testapp.home.stories.HomeScreenStories
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
) {

    HomeScreenContent(
        modifier = modifier
    )
}

@Composable
fun HomeScreenContent(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
    ) {
        HomeScreenTitle()
        HomeScreenStories()
        HomeScreenChatsTitle()
        HomeScreenChats()
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
        Icon(
            Icons.Default.Search,
            null,
            modifier = Modifier
                .size(32.dp)
                .clickable { }
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
        HomeScreenContent()
    }
}