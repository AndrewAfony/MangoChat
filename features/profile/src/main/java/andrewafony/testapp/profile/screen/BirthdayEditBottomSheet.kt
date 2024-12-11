package andrewafony.testapp.profile.screen

import andrewafony.testapp.designsystem.component.MangoButtonWithLoader
import andrewafony.testapp.designsystem.component.Picker
import andrewafony.testapp.designsystem.component.rememberPickerState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.unit.dp
import java.time.LocalDate
import java.time.Month

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BirthdayEditBottomSheet(
    modifier: Modifier = Modifier,
    sheetState: SheetState,
    birthday: LocalDate?,
    updateBirthday: (LocalDate) -> Unit,
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
                onDismiss()
                updateBirthday(it)
            }
        )
    }
}

@Composable
fun BirthdayEditBottomSheetContent(
    modifier: Modifier = Modifier,
    birthday: LocalDate?,
    updateBirthday: (LocalDate) -> Unit,
) {

    val currentYear = remember { LocalDate.now().year }

    val days = remember { (1..31).map { it.toString() } }
    val daysState = rememberPickerState()

    val months: List<Month> = remember {
        listOf(
            Month.JANUARY,
            Month.FEBRUARY,
            Month.MARCH,
            Month.APRIL,
            Month.MAY,
            Month.JUNE,
            Month.JULY,
            Month.AUGUST,
            Month.SEPTEMBER,
            Month.OCTOBER,
            Month.NOVEMBER,
            Month.DECEMBER,
        )
    }
    val monthState = rememberPickerState()

    val years = remember { (1901..currentYear).map { it.toString() } }
    val yearsState = rememberPickerState()

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
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
                startIndex = days.indexOfLast { it == birthday?.dayOfMonth.toString() }
            )
            Picker(
                state = monthState,
                modifier = Modifier
                    .weight(2f),
                items = months.map { it.name },
                visibleItemsCount = 5,
                startIndex = months.indexOfLast { it.value == birthday?.monthValue }
            )
            Picker(
                state = yearsState,
                modifier = Modifier
                    .weight(1f),
                items = years,
                visibleItemsCount = 5,
                startIndex = years.indexOfLast { it == birthday?.year.toString() }
            )
        }

        MangoButtonWithLoader(
            modifier = Modifier
                .fillMaxWidth(0.6f)
                .padding(vertical = 16.dp)
                .height(45.dp),
            text = "Сохранить",
            isLoader = false,
            onClick = {
                updateBirthday(
                    LocalDate.of(
                        yearsState.selectedItem.toInt(),
                        Month.of(months.indexOfLast { it.name == monthState.selectedItem } + 1),
                        daysState.selectedItem.toInt()
                    )
                )
            }
        )
    }

}

@Preview
@Composable
private fun BirthdayEditBottomSheetPrev() {
    andrewafony.testapp.designsystem.theme.MangoTestChatTheme {
        Surface {
            BirthdayEditBottomSheetContent(
                birthday = LocalDate.now(),
                updateBirthday = {}
            )
        }
    }
}