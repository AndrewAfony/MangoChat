package andrewafony.testapp.mangotestchat.feature_profile.presentation

import andrewafony.testapp.mangotestchat.theme.theme.MangoTestChatTheme
import andrewafony.testapp.mangotestchat.theme.theme.veryLightGray
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
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
    modifier: Modifier = Modifier
) {

    EditNameScreenContent(modifier = modifier)
}

@Composable
fun EditNameScreenContent(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Полное имя"
        )

        EditNameTextField(
            name = "Afanasiev",
            placeholder = "Фамилия"
        ) { }

        EditNameTextField(
            name = "Andrew",
            placeholder = "Имя"
        ) { }

        Button(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .height(60.dp)
                .padding(top = 8.dp),
            shape = RoundedCornerShape(16.dp),
            onClick = {  }
        ) {
            Text(
                text = "Сохранить"
            )
        }
    }
}

@Composable
fun EditNameTextField(
    modifier: Modifier = Modifier,
    name: String,
    placeholder: String,
    onEdit: (String) -> Unit,
) {

    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth(0.9f)
            .padding(vertical = 8.dp),
        value = name,
        onValueChange = onEdit,
        placeholder = {
            Text(
                text = placeholder,
            )
        },
        shape = RoundedCornerShape(16.dp),
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedBorderColor = Color.Transparent,
            focusedBorderColor = Color.Transparent,
            focusedTextColor = Color.Black,
            unfocusedTextColor = Color.Black,
            errorTextColor = Color.Red,
            unfocusedContainerColor = veryLightGray,
            focusedContainerColor = veryLightGray
        ),
        keyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.Words
        )
    )
}

@Preview
@Composable
private fun EditNameScreenPrev() {
    MangoTestChatTheme {
        Surface {
            EditNameScreenContent()
        }
    }
}