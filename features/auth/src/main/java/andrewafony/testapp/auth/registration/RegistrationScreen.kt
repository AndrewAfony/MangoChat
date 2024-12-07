package andrewafony.testapp.auth.registration

import andrewafony.testapp.designsystem.component.MangoButtonWithLoader
import andrewafony.testapp.designsystem.theme.MangoTestChatTheme
import andrewafony.testapp.designsystem.theme.veryLightGray
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.joelkanyi.jcomposecountrycodepicker.component.KomposeCountryCodePicker
import com.joelkanyi.jcomposecountrycodepicker.component.rememberKomposeCountryCodePickerState

@Composable
fun RegistrationScreen(
    modifier: Modifier = Modifier,
) {

    RegistrationScreenContent(
        modifier = modifier
    )
}

@Composable
fun RegistrationScreenContent(
    modifier: Modifier = Modifier,
) {

    val locale = Locale.current

    val phoneNumber = rememberSaveable { mutableStateOf("") }
    val state = rememberKomposeCountryCodePickerState(
        defaultCountryCode = locale.region
    )

    var isCode by remember { mutableStateOf(false) }
    var code by rememberSaveable { mutableStateOf("") }

    val width = LocalConfiguration.current.screenWidthDp
    val transition = updateTransition(targetState = isCode)

    val firstOffset by transition.animateDp(
        transitionSpec = { tween(durationMillis = 700) },
        label = "one"
    ) { isVisible ->
        if (!isVisible) 0.dp else -width.dp
    }

    val secondOffset by transition.animateDp(
        transitionSpec = { tween(durationMillis = 700) },
        label = "two"
    ) { isVisible ->
        if (!isVisible) width.dp else 0.dp
    }

    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Регистрация",
            style = MaterialTheme.typography.titleLarge
        )

        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {

            KomposeCountryCodePicker(
                modifier = Modifier
                    .offset(firstOffset)
                    .fillMaxWidth(0.9f)
                    .padding(vertical = 32.dp),
                text = phoneNumber.value,
                onValueChange = { phoneNumber.value = it },
                placeholder = { Text(text = "Номер телефона") },
                shape = RoundedCornerShape(16.dp),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = veryLightGray,
                    focusedContainerColor = veryLightGray,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                state = state,
            )

            OtpTextField(
                modifier = Modifier
                    .offset(secondOffset)
                    .padding(vertical = 32.dp),
                otpText = code,
                onOtpTextChange = { value, _ ->
                    code = value
                }
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            AnimatedVisibility(
                modifier = Modifier,
                visible = isCode
            ) {
                Box(
                    modifier = Modifier
                        .height(50.dp)
                        .width(60.dp)
                        .padding(end = 12.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .border(
                            1.dp,
                            color = MaterialTheme.colorScheme.primary,
                            shape = RoundedCornerShape(16.dp)
                        )
                        .clickable { isCode = !isCode },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Default.KeyboardArrowLeft, null)
                }
            }
            MangoButtonWithLoader(
                modifier = Modifier
                    .fillMaxWidth(),
                text = "Регистрация",
                isLoader = isCode,
                animatedContentAlignment = Alignment.CenterEnd,
                onClick = { isCode = !isCode }
            )
        }
    }
}

@Preview
@Composable
private fun RegistrationScreenPrev() {
    MangoTestChatTheme {
        Surface {
            RegistrationScreenContent()
        }
    }
}