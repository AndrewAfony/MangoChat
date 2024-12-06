package andrewafony.testapp.mangotestchat.feature_profile.presentation

import andrewafony.testapp.mangotestchat.core.presentation.Picker
import andrewafony.testapp.mangotestchat.theme.theme.MangoTestChatTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BirthdayEditBottomSheet(
    modifier: Modifier = Modifier,
    sheetState: SheetState,
    onDismiss: () -> Unit,
) {

    ModalBottomSheet(
        modifier = modifier,
        sheetState = sheetState,
        onDismissRequest = onDismiss
    ) {
        BirthdayEditBottomSheetContent()
    }
}

@Composable
fun BirthdayEditBottomSheetContent(
    modifier: Modifier = Modifier,
) {

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Picker(
                modifier = Modifier
                    .weight(1f),
                items = (0..31).map { it.toString() },
                visibleItemsCount = 5
            )
            Picker(
                modifier = Modifier
                    .weight(2f),
                items = listOf(
                    "Январь",
                    "Февраль",
                    "Март",
                    "Апрель",
                    "Май",
                    "Июнь",
                    "Июль",
                    "Август",
                    "Сентябрь",
                    "Октябрь",
                    "Ноябрь",
                    "Декабрь"
                ),
                visibleItemsCount = 5
            )
            Picker(
                modifier = Modifier
                    .weight(1f),
                items = (1900..2024).map { it.toString() },
                visibleItemsCount = 5
            )
        }

        Button(
            onClick = {}
        ) {
            Text("Сохранить")
        }
    }

}

@Preview
@Composable
private fun BirthdayEditBottomSheetPrev() {
    MangoTestChatTheme {
        Surface {
            BirthdayEditBottomSheetContent()
        }
    }
}