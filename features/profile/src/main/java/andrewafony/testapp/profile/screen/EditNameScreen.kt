package andrewafony.testapp.profile.screen

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

    val userState by viewModel.userState.collectAsStateWithLifecycle()

    EditNameScreenContent(
        modifier = modifier,
        name = userState.name,
        surname = userState.surname,
        updateName = viewModel::updateName,
        navigateBack = navigateBack
    )
}

@Composable
fun EditNameScreenContent(
    modifier: Modifier = Modifier,
    name: String,
    surname: String,
    updateName: (String, String) -> Unit,
    navigateBack: () -> Unit
) {

    var currentName by rememberSaveable { mutableStateOf(name) }
    var currentSurname by rememberSaveable { mutableStateOf(surname) }

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

        EditTextField(
            modifier = Modifier
                .padding(top = 12.dp),
            field = currentSurname,
            placeholder = "Фамилия",
            onEdit = { currentSurname = it }
        )

        EditTextField(
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
                updateName(currentName, currentSurname)
                navigateBack()
            }
        ) {
            Text(text = "Сохранить")
        }
    }
}

@Composable
fun EditTextField(
    modifier: Modifier = Modifier,
    field: String,
    placeholder: String,
    onEdit: (String) -> Unit,
) {

    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth(0.9f),
        value = field,
        onValueChange = onEdit,
        placeholder = {
            Text(text = placeholder)
        },
        shape = RoundedCornerShape(16.dp),
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedBorderColor = Color.Transparent,
            focusedBorderColor = Color.Transparent,
            focusedTextColor = Color.Black,
            unfocusedTextColor = Color.Black,
            errorTextColor = Color.Red,
            unfocusedContainerColor = andrewafony.testapp.designsystem.theme.veryLightGray,
            focusedContainerColor = andrewafony.testapp.designsystem.theme.veryLightGray
        ),
        keyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.Words
        )
    )
}

@Preview
@Composable
private fun EditNameScreenPrev() {
    andrewafony.testapp.designsystem.theme.MangoTestChatTheme {
        Surface {
            EditNameScreenContent(
                updateName = {_, _ ->},
                name = "Andrew",
                surname = "Afanasiev",
                navigateBack = {}
            )
        }
    }
}