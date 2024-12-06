package andrewafony.testapp.mangotestchat.feature_profile.presentation

import andrewafony.testapp.mangotestchat.theme.theme.MangoTestChatTheme
import andrewafony.testapp.mangotestchat.theme.theme.lightGray
import andrewafony.testapp.mangotestchat.theme.theme.veryLightGray
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ProfileAboutItem(modifier: Modifier = Modifier) {

    var textValue by remember { mutableStateOf("") }

    ProfileAboutItemContent(
        modifier = modifier,
        textValue = textValue,
        onTextChanged = { textValue = it }
    )
}

@Composable
fun ProfileAboutItemContent(
    modifier: Modifier = Modifier,
    textValue: String,
    onTextChanged: (String) -> Unit
) {

//    Box(
//        modifier = modifier
//            .fillMaxWidth()
//            .padding(16.dp)
//            .clip(RoundedCornerShape(16.dp))
//            .background(veryLightGray)
//    ) {
//
//
//    }
    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = 120.dp),
        value = textValue,
        onValueChange = {
            onTextChanged(it.take(300))
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
            unfocusedContainerColor = veryLightGray,
            focusedContainerColor = veryLightGray
        ),
        keyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.Sentences
        ),
        supportingText = {
//            if(textValue.length > 260) {
//                Text(
//                    text = "Лимит: ${textValue.length}/300",
//                    color = if(textValue.length < 290) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error
//                )
//            }
        }
    )
}

@Preview
@Composable
private fun ProfileAboutItemPrev() {
    MangoTestChatTheme {
        ProfileAboutItemContent(
            textValue = "",
            onTextChanged = {}
        )
    }
}