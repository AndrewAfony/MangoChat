package andrewafony.testapp.settings

import andrewafony.testapp.designsystem.theme.MangoTestChatTheme
import andrewafony.testapp.designsystem.theme.lightGray
import andrewafony.testapp.designsystem.theme.veryLightGray
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.valentinilk.shimmer.shimmer
import org.koin.androidx.compose.koinViewModel

@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    viewModel: SettingsViewModel = koinViewModel(),
    navigateToProfile: () -> Unit,
) {

    val settingsState by viewModel.settingsState.collectAsStateWithLifecycle()

    SettingsScreenContent(
        modifier = modifier,
        settingsState = settingsState,
        navigateToProfile = navigateToProfile
    )
}

@Composable
fun SettingsScreenContent(
    modifier: Modifier = Modifier,
    settingsState: SettingsState,
    navigateToProfile: () -> Unit,
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
            .padding(bottom = 80.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        when (settingsState) {
            is SettingsState.Loading -> {
                Box(
                    modifier = Modifier
                        .padding(top = 48.dp + 16.dp)
                        .shimmer()
                        .size(128.dp)
                        .clip(CircleShape)
                        .background(lightGray)
                )
                Box(
                    modifier = Modifier
                        .shimmer()
                        .padding(top = 16.dp)
                        .background(lightGray)
                ) {
                    Text(
                        text = "Some shimmered text",
                        color = lightGray,
                        style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.W500),
                    )
                }
                Spacer(modifier = Modifier.size(4.dp))
                Box(
                    modifier = Modifier
                        .shimmer()
                        .background(lightGray)
                ) {
                    Text(
                        text = "Some shimmered",
                        color = lightGray,
                        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.W300),
                    )
                }
            }

            is SettingsState.Error -> {
                AsyncImage(
                    model = "",
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    placeholder = painterResource(andrewafony.testapp.designsystem.R.drawable.person_placeholder),
                    error = painterResource(andrewafony.testapp.designsystem.R.drawable.person_placeholder),
                    modifier = Modifier
                        .shimmer()
                        .size(128.dp)
                        .clip(CircleShape)
                )
            }
            is SettingsState.Success -> {
                AsyncImage(
                    model = "",
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    placeholder = painterResource(andrewafony.testapp.designsystem.R.drawable.person_placeholder),
                    error = painterResource(andrewafony.testapp.designsystem.R.drawable.person_placeholder),
                    modifier = Modifier
                        .padding(top = 48.dp + 16.dp)
                        .size(128.dp)
                        .clip(CircleShape)
                )
                Text(
                    modifier = Modifier.padding(top = 16.dp),
                    text = settingsState.name,
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.W500),
                )
                Spacer(modifier = Modifier.size(4.dp))
                Text(
                    text = "@${settingsState.username}",
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.W300),
                )
            }
        }

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
                settingsState = SettingsState.Success(
                    image = Uri.EMPTY,
                    name = "Andrew Afanasiev",
                    username = "andrew_afony"
                ),
//                settingsState = SettingsState.Loading,
                navigateToProfile = {}
            )
        }
    }
}