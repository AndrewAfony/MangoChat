package andrewafony.testapp.auth.login

import andrewafony.testapp.designsystem.SetWindowSoftInputMode
import andrewafony.testapp.designsystem.component.MangoButtonWithLoader
import andrewafony.testapp.designsystem.component.MangoTextField
import andrewafony.testapp.designsystem.theme.MangoTestChatTheme
import android.view.WindowManager
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun RegistrationScreen(
    modifier: Modifier = Modifier,
    navigateToHome: () -> Unit,
) {

    RegistrationScreenContent(
        modifier = modifier,
        navigateToHome = navigateToHome
    )
}

@Composable
fun RegistrationScreenContent(
    modifier: Modifier = Modifier,
    navigateToHome: () -> Unit,
) {

    val allowedCharsRegex = remember { Regex("^[A-Za-z0-9\\-_]*$") }

    var username by rememberSaveable { mutableStateOf("") }
    var isUsernameError by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Регистрация",
            style = MaterialTheme.typography.titleLarge
        )
        MangoTextField(
            modifier = Modifier
                .padding(top = 32.dp),
            field = "+7 (952) 773-56-92",
            isEnabled = false,
            placeholder = "Номер телефона",
            onEdit = {}
        )
        MangoTextField(
            modifier = Modifier
                .padding(vertical = 12.dp),
            field = "",
            isSingleLine = true,
            placeholder = "Имя пользователя",
            keyboardCapitalization = KeyboardCapitalization.Words,
            onEdit = {}
        )
        MangoTextField(
            field = username,
            placeholder = "Username",
            isError = isUsernameError,
            isSingleLine = true,
            keyboardType = KeyboardType.Ascii,
            onEdit = {
                username = it
                isUsernameError = !it.matches(allowedCharsRegex)
            }
        )
        AnimatedVisibility(isUsernameError) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 36.dp, top = 8.dp),
                text = "Allowed characters: A-Z, a-z, 0-9, '-', '_'",
                color = Color.Red,
                style = MaterialTheme.typography.labelMedium
            )
        }

        MangoButtonWithLoader(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp, top = 12.dp),
            text = "Войти",
            isLoader = false,
            onClick = {}
        )
    }
}

@Preview
@Composable
private fun LoginScreenPrev() {
    MangoTestChatTheme {
        Surface {
            RegistrationScreenContent(
                navigateToHome = {},
            )
        }
    }
}