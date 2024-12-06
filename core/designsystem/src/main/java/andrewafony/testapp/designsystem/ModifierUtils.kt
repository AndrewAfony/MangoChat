package andrewafony.testapp.designsystem

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed

const val TEST_IMAGE = "https://chi-russia.ru/upload/resize_cache/iblock/1c7/790_790_1/ttq27yl1iahlbmj2vyzzeog546idoghc.jpg"

fun Modifier.disableClickAndRipple(): Modifier = composed {
    clickable(
        enabled = false,
        indication = null,
        interactionSource = remember { MutableInteractionSource() },
        onClick = { },
    )
}