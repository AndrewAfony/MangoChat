package andrewafony.testapp.profile

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun EditNameScreen(
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit
) {

    EditNameScreenContent(
        modifier = modifier,
        navigateBack = navigateBack
    )
}

@Composable
fun EditNameScreenContent(
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit
) {

    Column(
        modifier = modifier
            .fillMaxSize(),
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
            field = "Afanasiev",
            placeholder = "Фамилия",
            onEdit = {}
        )

        EditTextField(
            modifier = Modifier
                .padding(vertical = 12.dp)
                .height(56.dp),
            field = "Andrew",
            placeholder = "Имя",
            onEdit = {}
        )

        Button(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .height(56.dp),
            shape = RoundedCornerShape(16.dp),
            onClick = {
                // todo save
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
                navigateBack = {}
            )
        }
    }
}