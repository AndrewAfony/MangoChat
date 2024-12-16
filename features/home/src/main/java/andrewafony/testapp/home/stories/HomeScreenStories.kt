package andrewafony.testapp.home.stories

import andrewafony.testapp.shared_ui.utils.TEST_IMAGE
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreenStories(
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier,
        contentPadding = PaddingValues(16.dp)
    ) {
        item {
            NewStoryItem()
        }
        items(5) {
            StoryItem(
                image = andrewafony.testapp.shared_ui.utils.TEST_IMAGE,
                name = "Name $it"
            ) { }
        }
    }
}

@Preview
@Composable
private fun HomeScreenStoriesPrev() {
    HomeScreenStories()
}