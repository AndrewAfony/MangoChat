package andrewafony.testapp.auth.login

import andrewafony.testapp.designsystem.SetWindowSoftInputMode
import andrewafony.testapp.designsystem.component.MangoButtonWithLoader
import andrewafony.testapp.designsystem.theme.MangoTestChatTheme
import andrewafony.testapp.designsystem.theme.veryLightGray
import andrewafony.testapp.designsystem.toast
import android.view.WindowManager
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateIntOffset
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.joelkanyi.jcomposecountrycodepicker.component.KomposeCountryCodePicker
import com.joelkanyi.jcomposecountrycodepicker.component.rememberKomposeCountryCodePickerState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    viewModel: AuthViewModel = koinViewModel(),
    navigateToRegistration: (String) -> Unit,
    navigateToHome: () -> Unit,
) {

    val authState by viewModel.authState.collectAsStateWithLifecycle()
    val authUiState by viewModel.authUiState.collectAsStateWithLifecycle()

    SetWindowSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)

    LoginScreenContent(
        modifier = modifier,
        authState = authState,
        authUiState = authUiState,
        isError = viewModel.error,
        updateUiState = viewModel::updateUiState,
        handleButton = viewModel::handleButton,
        backToPhone = viewModel::backToPhone,
        clearState = viewModel::clear,
        navigateToRegistration = navigateToRegistration,
        navigateToHome = navigateToHome
    )
}

@Composable
fun LoginScreenContent(
    modifier: Modifier = Modifier,
    authState: AuthState,
    authUiState: AuthUiState,
    isError: SharedFlow<Boolean>,
    updateUiState: (UiEvent) -> Unit,
    handleButton: (String) -> Unit,
    backToPhone: () -> Unit,
    clearState: () -> Unit,
    navigateToRegistration: (String) -> Unit,
    navigateToHome: () -> Unit,
) {

    val context = LocalContext.current

    val locale = Locale.current
    val phoneState = rememberKomposeCountryCodePickerState(
        defaultCountryCode = locale.region
    )

    val width = LocalConfiguration.current.screenWidthDp
    val transition = updateTransition(targetState = authUiState.isCode)

    val firstOffset by transition.animateIntOffset(
        transitionSpec = { tween(durationMillis = 700) },
        label = "one"
    ) { isVisible ->
        IntOffset(if (!isVisible) 0 else -width * 3, 0)
    }

    val secondOffset by transition.animateIntOffset(
        transitionSpec = { tween(durationMillis = 700) },
        label = "two"
    ) { isVisible ->
        IntOffset(if (!isVisible) width * 3 else 0, 0)
    }

    LaunchedEffect(Unit) {
       isError.collectLatest {
           context.toast("Error")
       }
    }

    LaunchedEffect(authState) {

        if (authState is AuthState.Registration) {
            navigateToRegistration(phoneState.getFullyFormattedPhoneNumber())
            clearState()
        }
        if (authState is AuthState.SignIn) {
            navigateToHome()
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Авторизация",
            style = MaterialTheme.typography.titleLarge
        )

        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {

            KomposeCountryCodePicker(
                modifier = Modifier
                    .offset { firstOffset }
                    .fillMaxWidth(0.9f)
                    .padding(vertical = 32.dp),
                text = authUiState.phone,
                onValueChange = { updateUiState(UiEvent.Phone(it)) },
                placeholder = { Text(text = "Номер телефона") },
                shape = RoundedCornerShape(16.dp),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = veryLightGray,
                    focusedContainerColor = veryLightGray,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                state = phoneState,
            )

            OtpTextField(
                modifier = Modifier
                    .offset { secondOffset }
                    .padding(vertical = 32.dp),
                otpText = authUiState.code,
                onOtpTextChange = { value, _ -> updateUiState(UiEvent.Code(value)) }
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            AnimatedVisibility(
                modifier = Modifier,
                visible = authState is AuthState.EnterCode
            ) {
                Box(
                    modifier = Modifier
                        .height(50.dp)
                        .width(60.dp)
                        .padding(end = 12.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .border(
                            width = 1.dp,
                            color = MaterialTheme.colorScheme.primary,
                            shape = RoundedCornerShape(16.dp)
                        )
                        .clickable { backToPhone() },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Default.KeyboardArrowLeft, null)
                }
            }
            MangoButtonWithLoader(
                modifier = Modifier
                    .fillMaxWidth(0.9f),
                text = "Войти",
                isEnabled = when(authState) {
                    is AuthState.SignedOut -> phoneState.isPhoneNumberValid()
                    is AuthState.EnterCode -> authUiState.isCodeValid
                    else -> true
                },
                isLoader = authState is AuthState.Loading,
                animatedContentAlignment = Alignment.CenterEnd,
                onClick = { if (authState !is AuthState.Loading) handleButton(phoneState.getFullPhoneNumber()) }
            )
        }
    }
}

@Preview
@Composable
private fun LogScreenPrev() {
    MangoTestChatTheme {
        Surface {
            LoginScreenContent(
                authUiState = AuthUiState("", "", false),
                authState = AuthState.SignedOut,
                updateUiState = {},
                handleButton = {},
                backToPhone = {},
                clearState = {},
                isError = MutableSharedFlow<Boolean>(),
                navigateToRegistration = {},
                navigateToHome = {},
            )
        }
    }
}
