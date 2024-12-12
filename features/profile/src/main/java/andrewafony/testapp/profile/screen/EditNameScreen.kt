package andrewafony.testapp.profile.screen

import andrewafony.testapp.designsystem.component.MangoTextField
import andrewafony.testapp.profile.ProfileState
import andrewafony.testapp.profile.ProfileViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun EditNameScreen(
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel,
    navigateBack: () -> Unit
) {

    val profileState by viewModel.userState.collectAsStateWithLifecycle()
    val name = (profileState as ProfileState.Success).user.name

    EditNameScreenContent(
        modifier = modifier,
        name = name,
        updateName = viewModel::updateName,
        navigateBack = navigateBack
    )
}

@Composable
fun EditNameScreenContent(
    modifier: Modifier = Modifier,
    name: String,
    updateName: (String) -> Unit,
    navigateBack: () -> Unit
) {

    var currentName by rememberSaveable { mutableStateOf(name) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        ProfileScreenTopBar(navigateBack = navigateBack)

        Text(
            modifier = Modifier.padding(top = 24.dp),
            text = "Полное имя",
            style = MaterialTheme.typography.titleMedium
        )

        MangoTextField(
            modifier = Modifier
                .padding(vertical = 12.dp)
                .height(56.dp),
            field = currentName,
            placeholder = "Имя",
            onEdit = { currentName = it }
        )

        Button(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .height(56.dp),
            shape = RoundedCornerShape(16.dp),
            onClick = {
                updateName(currentName)
                navigateBack()
            }
        ) {
            Text(text = "Сохранить")
        }
    }
}

@Preview
@Composable
private fun EditNameScreenPrev() {
    andrewafony.testapp.designsystem.theme.MangoTestChatTheme {
        Surface {
            EditNameScreenContent(
                updateName = {},
                name = "Andrew",
                navigateBack = {}
            )
        }
    }
}