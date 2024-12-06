package andrewafony.testapp.mangotestchat.feature_home.presentation

import andrewafony.testapp.mangotestchat.R
import andrewafony.testapp.mangotestchat.core.presentation.icons.More_horiz
import andrewafony.testapp.mangotestchat.feature_chats.presentation.HomeScreenChatsContent
import andrewafony.testapp.mangotestchat.feature_home.presentation.stories.HomeScreenStories
import andrewafony.testapp.mangotestchat.theme.theme.MangoTestChatTheme
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

const val TEST_IMAGE = "https://chi-russia.ru/upload/resize_cache/iblock/1c7/790_790_1/ttq27yl1iahlbmj2vyzzeog546idoghc.jpg"

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
        HomeScreenChatsContent()
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
            text = stringResource(R.string.app_name),
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
            imageVector = More_horiz,
            "",
            modifier = Modifier
                .clickable {  }
        )
    }
}



@Preview
@Composable
private fun HomeScreenPrev() {
    MangoTestChatTheme {
        HomeScreenContent()
    }
}