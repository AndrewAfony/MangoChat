package andrewafony.testapp.profile.screen

import andrewafony.testapp.designsystem.component.Picker
import andrewafony.testapp.designsystem.component.rememberPickerState
import andrewafony.testapp.domain.model.Birthday
import andrewafony.testapp.domain.model.Month
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BirthdayEditBottomSheet(
    modifier: Modifier = Modifier,
    sheetState: SheetState,
    birthday: Birthday,
    updateBirthday: (Birthday) -> Unit,
    onDismiss: () -> Unit,
) {

    ModalBottomSheet(
        modifier = modifier,
        sheetState = sheetState,
        containerColor = MaterialTheme.colorScheme.surface,
        onDismissRequest = onDismiss
    ) {
        BirthdayEditBottomSheetContent(
            birthday = birthday,
            updateBirthday = {
                updateBirthday(it)
                onDismiss()
            }
        )
    }
}

@Composable
fun BirthdayEditBottomSheetContent(
    modifier: Modifier = Modifier,
    birthday: Birthday,
    updateBirthday: (Birthday) -> Unit,
) {

    val days = remember { (1..31).map { it.toString() } }
    val daysState = rememberPickerState()

    val months: List<Month> = remember {
        listOf(
            Month("Январь", "01"),
            Month("Февраль", "02"),
            Month("Март", "03"),
            Month("Апрель", "04"),
            Month("Май", "05"),
            Month("Июнь", "06"),
            Month("Июль", "07"),
            Month("Август", "08"),
            Month("Сентябрь", "09"),
            Month("Октябрь", "10"),
            Month("Ноябрь", "11"),
            Month("Декабрь", "12"),
        )
    }
    val monthState = rememberPickerState()

    val years = remember { (1900..2024).map { it.toString() } }
    val yearsState = rememberPickerState()

    Column(
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Picker(
                state = daysState,
                modifier = Modifier
                    .weight(1f),
                items = days,
                visibleItemsCount = 5,
                startIndex = days.indexOf(birthday.day)
            )
            Picker(
                state = monthState,
                modifier = Modifier
                    .weight(2f),
                items = months.map { it.name },
                visibleItemsCount = 5,
                startIndex = months.indexOf(birthday.month)
            )
            Picker(
                state = yearsState,
                modifier = Modifier
                    .weight(1f),
                items = years,
                visibleItemsCount = 5,
                startIndex = years.indexOf(birthday.year)
            )
        }

        Button(
            onClick = {
                updateBirthday(
                    Birthday(
                        yearsState.selectedItem,
                        months[months.indexOfFirst { it.name == monthState.selectedItem }],
                        daysState.selectedItem
                    )
                )
            }
        ) {
            Text("Сохранить")
        }
    }

}

@Preview
@Composable
private fun BirthdayEditBottomSheetPrev() {
    andrewafony.testapp.designsystem.theme.MangoTestChatTheme {
        Surface {
            BirthdayEditBottomSheetContent(
                birthday = Birthday("2001", Month("Май", "05"), "24"),
                updateBirthday = {}
            )
        }
    }
}