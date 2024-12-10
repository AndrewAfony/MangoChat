package andrewafony.testapp.auth.registration

import andrewafony.testapp.designsystem.component.MangoButtonWithLoader
import andrewafony.testapp.designsystem.component.MangoTextField
import andrewafony.testapp.designsystem.theme.MangoTestChatTheme
import andrewafony.testapp.designsystem.toast
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel

@Composable
fun RegistrationScreen(
    modifier: Modifier = Modifier,
    number: String,
    viewModel: RegistrationViewModel = koinViewModel(),
    navigateBack: () -> Unit,
    navigateToHome: () -> Unit,
) {

    val regState by viewModel.regState.collectAsStateWithLifecycle()

    RegistrationScreenContent(
        modifier = modifier,
        number = number,
        regState = regState,
        register = { viewModel.register(phone = number) },
        updateUiState = viewModel::updateUiState,
        navigateBack = navigateBack,
        navigateToHome = navigateToHome
    )
}

@Composable
fun RegistrationScreenContent(
    modifier: Modifier = Modifier,
    number: String,
    regState: RegState,
    register: () -> Unit,
    navigateToHome: () -> Unit,
    updateUiState: (UiEvent) -> Unit,
    navigateBack: () -> Unit,
) {

    val context = LocalContext.current

    val allowedCharsRegex = remember { Regex("^[A-Za-z0-9\\-_]*$") }
    var isUsernameError by rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(regState.isError, regState.isSuccess) {
        if (regState.isError) {
            context.toast("Something went wrong")
        }
        if (regState.isSuccess) {
            navigateToHome()
        }
    }

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
            field = number,
            isEnabled = false,
            placeholder = "Номер телефона",
            onEdit = {}
        )
        MangoTextField(
            modifier = Modifier
                .padding(vertical = 12.dp),
            field = regState.name,
            isSingleLine = true,
            placeholder = "Имя пользователя",
            keyboardCapitalization = KeyboardCapitalization.Words,
            onEdit = { updateUiState(UiEvent.Name(it)) }
        )
        MangoTextField(
            field = regState.username,
            placeholder = "Username",
            isError = isUsernameError,
            isSingleLine = true,
            keyboardType = KeyboardType.Ascii,
            onEdit = {
                updateUiState(UiEvent.Username(it))
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
            isEnabled = !isUsernameError,
            isLoader = regState.isLoading,
            onClick = {
                if (!regState.isLoading) register()
            }
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.TopStart
    ) {
        Icon(
            imageVector = Icons.Outlined.KeyboardArrowLeft,
            contentDescription = null,
            modifier = Modifier
                .clip(CircleShape)
                .clickable { navigateBack() }
                .padding(16.dp)
        )
    }
}

@Preview
@Composable
private fun LoginScreenPrev() {
    MangoTestChatTheme {
        Surface {
            RegistrationScreenContent(
                navigateBack = {},
                number = "+7 9991342342",
                regState = RegState(),
                register = {},
                updateUiState = {},
                navigateToHome = {},
            )
        }
    }
}