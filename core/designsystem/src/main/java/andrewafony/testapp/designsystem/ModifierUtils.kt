package andrewafony.testapp.designsystem

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed

const val TEST_IMAGE = "https://avatars.mds.yandex.net/i?id=780d5144f133ebfb68341d04d4e794dd_l-5237646-images-thumbs&n=13"

fun Modifier.disableClickAndRipple(): Modifier = composed {
    clickable(
        enabled = false,
        indication = null,
        interactionSource = remember { MutableInteractionSource() },
        onClick = { },
    )
}