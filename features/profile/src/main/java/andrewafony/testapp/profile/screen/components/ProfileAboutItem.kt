package andrewafony.testapp.profile.screen.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ProfileAboutItem(
    modifier: Modifier = Modifier,
    prevAbout: String,
    updateAbout: (String) -> Unit,
) {

    var textValue by rememberSaveable { mutableStateOf(prevAbout) }

    ProfileAboutItemContent(
        modifier = modifier,
        prevAbout = prevAbout,
        textValue = textValue,
        updateAbout = updateAbout,
        onTextChanged = { textValue = it }
    )
}

@Composable
fun ProfileAboutItemContent(
    modifier: Modifier = Modifier,
    prevAbout: String,
    textValue: String,
    updateAbout: (String) -> Unit,
    onTextChanged: (String) -> Unit
) {

    Column(
        horizontalAlignment = Alignment.End
    ) {
        AnimatedVisibility(
            visible = textValue != prevAbout
        ) {
            TextButton(onClick = {updateAbout(textValue) }, modifier = Modifier.padding(end = 16.dp)) { Text("Сохранить") }
        }
        OutlinedTextField(
            modifier = modifier
                .fillMaxWidth()
                .defaultMinSize(minHeight = 120.dp),
            value = textValue,
            onValueChange = {
                onTextChanged(it.take(150))
            },
            placeholder = {
                Text(
                    text = "Напишите немного о себе",
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.W300,
                    color = Color.Gray
                )
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
                capitalization = KeyboardCapitalization.Sentences
            ),
            supportingText = {
                if(textValue.length > 120) {
                    Text(
                        text = "Лимит: ${textValue.length}/150",
                        color = if(textValue.length < 140) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error
                    )
                }
            }
        )
    }

}

@Preview
@Composable
private fun ProfileAboutItemPrev() {
    andrewafony.testapp.designsystem.theme.MangoTestChatTheme {
        ProfileAboutItemContent(
            textValue = "",
            prevAbout = "Nice",
            updateAbout = {},
            onTextChanged = {}
        )
    }
}