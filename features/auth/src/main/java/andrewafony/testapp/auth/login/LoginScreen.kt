package andrewafony.testapp.auth.login

import andrewafony.testapp.auth.registration.RegistrationScreenContent
import andrewafony.testapp.designsystem.component.MangoTextField
import andrewafony.testapp.designsystem.theme.MangoTestChatTheme
import andrewafony.testapp.designsystem.theme.veryLightGray
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier
) {

    LoginScreenContent(modifier = modifier)
}

@Composable
fun LoginScreenContent(
    modifier: Modifier = Modifier
) {

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

        Box(
            modifier = Modifier
                .animateContentSize(alignment = Alignment.CenterEnd)
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp, top = 12.dp)
                .height(50.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(MaterialTheme.colorScheme.primary)
                .clickable {  },
            contentAlignment = Alignment.Center
        ) {
            if (true) {
                CircularProgressIndicator(
                    modifier = Modifier.size(24.dp),
                    strokeWidth = 1.dp,
                    color = Color.White
                )
            } else {
                Text(text = "Авторизация")
            }
        }
    }

}

@Preview
@Composable
private fun LoginScreenPrev() {
    MangoTestChatTheme {
        Surface {
            LoginScreenContent()
        }
    }
}