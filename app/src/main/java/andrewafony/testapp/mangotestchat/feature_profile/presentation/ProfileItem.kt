package andrewafony.testapp.mangotestchat.feature_profile.presentation

import andrewafony.testapp.mangotestchat.theme.theme.MangoTestChatTheme
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ProfileItem(
    modifier: Modifier = Modifier,
    title: String,
    content: String,
    isChangeable: Boolean,
    onClick: () -> Unit = {}
) {

    ProfileItemContent(
        modifier = modifier,
        title = title,
        content = content,
        isChangeable = isChangeable,
        onClick = onClick
    )
}

@Composable
fun ProfileItemContent(
    modifier: Modifier = Modifier,
    title: String,
    content: String,
    isChangeable: Boolean,
    onClick: () -> Unit
) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable { if (isChangeable) onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.W300,
                color = Color.DarkGray
            )
            Spacer(modifier = Modifier.size(4.dp))
            Text(text = content)
        }

        if (isChangeable) {
            Icon(
                imageVector = Icons.AutoMirrored.Outlined.KeyboardArrowRight,
                contentDescription = null,
                tint = Color.Gray
            )
        }
    }

}

@Preview
@Composable
private fun ProfileItemPrev() {
    MangoTestChatTheme {
        Surface(
            color = MaterialTheme.colorScheme.surface
        ) {
            ProfileItemContent(
                title = "Номер телефона",
                content = "+7 (952) 773-56-92",
                isChangeable = true,
                onClick = {}
            )
        }
    }
}