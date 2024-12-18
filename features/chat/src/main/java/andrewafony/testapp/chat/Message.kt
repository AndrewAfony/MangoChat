package andrewafony.testapp.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Message(
    modifier: Modifier = Modifier,
    isUserMe: Boolean = true,
    message: String
) {

    val screenWidth = LocalConfiguration.current.screenWidthDp

    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = if (isUserMe) Arrangement.End else Arrangement.Start
    ) {
        Box (
            modifier = Modifier
                .padding(vertical = 4.dp)
                .clip(RoundedCornerShape(24.dp))
                .background(if (isUserMe) MaterialTheme.colorScheme.primary else andrewafony.testapp.designsystem.theme.lightGray)
                .padding(16.dp)
                .wrapContentWidth()
                .widthIn(max = screenWidth.dp * 0.6f),
        ) {
            Text(
                text = message
            )
        }

    }

}

@Preview
@Composable
private fun MessagePrev() {
    andrewafony.testapp.designsystem.theme.MangoTestChatTheme {
        Message(
            message = "kdnsfsdjfn jflsdjfsdkjfnsdkj fjdnfk fjsdnfkdj skdjfsndk",
        )
    }
}