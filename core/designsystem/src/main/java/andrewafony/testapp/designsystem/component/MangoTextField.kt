package andrewafony.testapp.designsystem.component

import andrewafony.testapp.designsystem.theme.veryLightGray
import android.telephony.PhoneNumberUtils
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun MangoTextField(
    modifier: Modifier = Modifier,
    field: String,
    placeholder: String,
    leadingIcon: (@Composable () -> Unit)? = null,
    isError: Boolean = false,
    isEnabled: Boolean = true,
    isSingleLine: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardCapitalization: KeyboardCapitalization = KeyboardCapitalization.None,
    keyboardType: KeyboardType = KeyboardType.Unspecified,
    onEdit: (String) -> Unit,
) {

    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth(0.9f),
        enabled = isEnabled,
        value = field,
        onValueChange = onEdit,
        placeholder = {
            Text(text = placeholder)
        },
        leadingIcon = leadingIcon,
        singleLine = isSingleLine,
        isError = isError,
        visualTransformation = visualTransformation,
        shape = RoundedCornerShape(16.dp),
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedBorderColor = Color.Transparent,
            focusedBorderColor = Color.Transparent,
            focusedTextColor = Color.Black,
            unfocusedTextColor = Color.Black,
            errorTextColor = Color.Red,
            errorBorderColor = Color.Red.copy(alpha = 0.5f),
            errorContainerColor = veryLightGray,
            disabledTextColor = Color.Gray,
            disabledContainerColor = Color.LightGray,
            disabledBorderColor = Color.Transparent,
            unfocusedContainerColor = veryLightGray,
            focusedContainerColor = veryLightGray
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            capitalization = keyboardCapitalization
        )
    )
}

@Preview
@Composable
private fun MangoTextFieldPrev() {
    Box(
        modifier = Modifier.fillMaxSize().background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        MangoTextField(
            field = "Имя",
            placeholder = "",
            isError = true,
            isEnabled = false
        ) { }
    }
}