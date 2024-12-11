package andrewafony.testapp.profile.screen

import andrewafony.testapp.designsystem.component.MangoTextField
import andrewafony.testapp.designsystem.theme.MangoTestChatTheme
import andrewafony.testapp.profile.ProfileState
import andrewafony.testapp.profile.ProfileViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun EditCityScreen(
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel,
    navigateBack: () -> Unit
) {

    val userState by viewModel.profileState.collectAsStateWithLifecycle()
    val userCity = (userState as ProfileState.Success).user.city

    EditCityScreenContent(
        modifier = modifier,
        city = userCity,
        updateCity = viewModel::updateCity,
        navigateBack = navigateBack
    )
}

@Composable
fun EditCityScreenContent(
    modifier: Modifier = Modifier,
    city: String?,
    updateCity: (String) -> Unit,
    navigateBack: () -> Unit
) {

    var currentCity by rememberSaveable { mutableStateOf(city) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        ProfileScreenTopBar(navigateBack = navigateBack)

        Text(
            modifier = Modifier.padding(top = 24.dp),
            text = "Город",
            style = MaterialTheme.typography.titleMedium
        )

        MangoTextField(
            modifier = Modifier.padding(vertical = 12.dp),
            field = currentCity ?: "",
            placeholder = "Город",
            onEdit = { currentCity = it }
        )

        Button(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .height(56.dp),
            shape = RoundedCornerShape(16.dp),
            onClick = {
                updateCity(currentCity ?: "")
                navigateBack()
            }
        ) {
            Text(text = "Сохранить")
        }
    }
}

@Preview
@Composable
private fun EditCityScreenPrev() {
    MangoTestChatTheme {
        Surface {
            EditCityScreenContent(
                city = "Нижний Новгород",
                updateCity = {},
                navigateBack = {}
            )
        }
    }
}