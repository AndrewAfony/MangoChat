package andrewafony.testapp.settings

import andrewafony.testapp.designsystem.theme.MangoTestChatTheme
import andrewafony.testapp.designsystem.theme.veryLightGray
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage

@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    navigateToProfile: () -> Unit
) {

    SettingsScreenContent(
        modifier = modifier,
        navigateToProfile = navigateToProfile
    )
}

@Composable
fun SettingsScreenContent(
    modifier: Modifier = Modifier,
    navigateToProfile: () -> Unit
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = andrewafony.testapp.designsystem.R.drawable.test_image,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(top = 48.dp + 16.dp)
                .size(128.dp)
                .clip(CircleShape)
        )

        Text(
            modifier = Modifier.padding(top = 16.dp),
            text = "Andrew Afanasiev",
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.W500),
        )
        Spacer(modifier = Modifier.size(4.dp))
        Text(
            text = "@andrew_afony",
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.W300),
        )

        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 24.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(veryLightGray)
        ) {

            SettingsItem(
                title = "Мой профиль",
                onClick = navigateToProfile
            )
        }
    }
}

@Preview
@Composable
private fun SettingsScreenPrev() {
    MangoTestChatTheme {
        Surface {
            SettingsScreenContent(
                navigateToProfile = {}
            )
        }
    }
}