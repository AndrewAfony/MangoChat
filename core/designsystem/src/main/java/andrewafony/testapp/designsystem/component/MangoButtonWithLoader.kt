package andrewafony.testapp.designsystem.component

import andrewafony.testapp.designsystem.theme.MangoTestChatTheme
import andrewafony.testapp.designsystem.theme.lightGray
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun MangoButtonWithLoader(
    modifier: Modifier = Modifier,
    text: String,
    isEnabled: Boolean = true,
    isLoader: Boolean,
    animatedContentAlignment: Alignment = Alignment.Center,
    onClick: () -> Unit
) {

    val interaction = remember { MutableInteractionSource() }

    val clickableModifier = remember(isEnabled) {
        if (isEnabled)
            Modifier.clickable { onClick() }
        else
            Modifier.clickable(
                interactionSource = interaction,
                indication = null
            ) {  }
    }

    Box(
        modifier = Modifier
            .animateContentSize(alignment = animatedContentAlignment)
            .then(modifier)
            .height(50.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(if (isEnabled) MaterialTheme.colorScheme.primary else Color.LightGray)
            .then(clickableModifier),
        contentAlignment = Alignment.Center
    ) {
        if (isLoader) {
            CircularProgressIndicator(
                modifier = Modifier.size(24.dp),
                strokeWidth = 1.dp,
                color = Color.White
            )
        } else {
            Text(text = text)
        }
    }
}

@Preview
@Composable
private fun MangoButtonWithLoaderPrev() {
    MangoTestChatTheme {
        Box(modifier = Modifier.fillMaxSize().background(Color.White)) {
            MangoButtonWithLoader(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, top = 12.dp),
                text = "Авторизация",
                isEnabled = true,
                isLoader = false,
                onClick = {}
            )
        }
    }
}