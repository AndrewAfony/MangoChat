package andrewafony.testapp.profile

import andrewafony.testapp.designsystem.theme.MangoTestChatTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun EditCityScreen(
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit
) {

    EditCityScreenContent(
        modifier = modifier,
        navigateBack = navigateBack
    )
}

@Composable
fun EditCityScreenContent(
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit
) {

    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        ProfileScreenTopBar(navigateBack = navigateBack)

        Text(
            modifier = Modifier.padding(top = 24.dp),
            text = "Город",
            style = MaterialTheme.typography.titleMedium
        )
        
        EditTextField(
            modifier = Modifier.padding(vertical = 12.dp),
            field = "",
            placeholder = "Город",
            onEdit = {}
        )

        Button(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .height(56.dp),
            shape = RoundedCornerShape(16.dp),
            onClick = {
                // todo save
                navigateBack()
            }
        ) {
            Text(text = "Сохранить")
        }
    }
}

@Preview
@Composable
private fun EditCityScreenPrev() {
    MangoTestChatTheme {
        Surface {
            EditCityScreenContent(
                navigateBack = {}
            )
        }
    }
}