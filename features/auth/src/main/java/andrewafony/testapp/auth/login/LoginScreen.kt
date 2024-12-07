package andrewafony.testapp.auth.login

import andrewafony.testapp.designsystem.component.MangoButtonWithLoader
import andrewafony.testapp.designsystem.component.MangoTextField
import andrewafony.testapp.designsystem.theme.MangoTestChatTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withLink
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    navigateToRegistration: () -> Unit,
    navigateToHome: () -> Unit
) {

    LoginScreenContent(
        modifier = modifier,
        navigateToHome = navigateToHome,
        navigateToRegistration = navigateToRegistration
    )
}

@Composable
fun LoginScreenContent(
    modifier: Modifier = Modifier,
    navigateToHome: () -> Unit,
    navigateToRegistration: () -> Unit
) {

    val linkText = remember {
        buildAnnotatedString {
            withStyle(style = SpanStyle(color = Color.Gray)) {
                append("Нет аккаунта? ")
            }
            withLink(
                LinkAnnotation.Clickable(tag = "registerLink", styles = TextLinkStyles(
                    style = SpanStyle(color = Color.Blue, fontWeight = FontWeight.W500)
                ), linkInteractionListener = { navigateToRegistration() })
            ) {
                append("Регистрация")
            }
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Авторизация",
            style = MaterialTheme.typography.titleLarge
        )
        MangoTextField(
            modifier = Modifier
                .padding(top = 32.dp),
            field = "+7 (952) 773-56-92",
            isEnabled = false,
            placeholder = "Имя пользователя",
            onEdit = {}
        )
        MangoTextField(
            modifier = Modifier
                .padding(vertical = 12.dp),
            field = "",
            placeholder = "Имя пользователя",
            onEdit = {}
        )
        MangoTextField(
            field = "",
            placeholder = "Username",
            onEdit = {}
        )

        MangoButtonWithLoader(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp, top = 12.dp),
            text = "Авторизация",
            isLoader = true,
            onClick = {}
        )

        Text(
            modifier = Modifier
                .padding(top = 24.dp),
            text = linkText
        )
    }
}

@Preview
@Composable
private fun LoginScreenPrev() {
    MangoTestChatTheme {
        Surface {
            LoginScreenContent(
                navigateToHome = {},
                navigateToRegistration = {}
            )
        }
    }
}