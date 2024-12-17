package andrewafony.testapp.mangotestchat.main_screen

import andrewafony.testapp.designsystem.icons.ChatIcon
import andrewafony.testapp.designsystem.icons.IconContact
import andrewafony.testapp.designsystem.theme.lightGray
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ExitToApp
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun BottomNavigationButtonPopup(
    isPopup: Boolean,
    onPopupChange: (Boolean) -> Unit,
    isCurrentHomeRoute: Boolean
) {

    val animatedAlpha by animateFloatAsState(
        targetValue = if (isPopup) 0.8f else 0f,
        label = "background_alpha_animation"
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 80.dp)
            .graphicsLayer { alpha = animatedAlpha }
            .background(lightGray)
            .then(if (isPopup) {
                Modifier
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ) { onPopupChange(false) }
            } else {
                Modifier
            })
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 80.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        AnimatedVisibility(
            visible = isPopup,
            enter = slideInVertically(initialOffsetY = { it / 2 }) +
                    scaleIn(),
            exit = slideOutVertically(targetOffsetY = { it / 2 }) +
                    scaleOut()
        ) {
            MainButtonPopup(
                modifier = Modifier
                    .padding(bottom = 16.dp),
                isCurrentHomeRoute = isCurrentHomeRoute
            )
        }
    }

    BackHandler(
        enabled = isPopup
    ) {
        onPopupChange(false)
    }
}

@Composable
fun MainButtonPopup(
    modifier: Modifier = Modifier,
    isCurrentHomeRoute: Boolean,
) {

    AnimatedContent(
        targetState = isCurrentHomeRoute,
        transitionSpec = {
            scaleIn(initialScale = 0.9f) + fadeIn() togetherWith
                    scaleOut(targetScale = 0.9f) + fadeOut()
        }
    ) { state ->
        Column(
            modifier = modifier
                .fillMaxWidth(0.9f)
                .clip(RoundedCornerShape(24.dp))
                .background(MaterialTheme.colorScheme.surface)
        ) {
            if (state) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { }
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Icon(
                        imageVector = ChatIcon,
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                    Column(
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Text(
                            text = "Создать чат",
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.W500
                        )
                        Text(text = "Отправить сообщение контакту")
                    }
                }
                HorizontalDivider()
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { }
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Icon(
                        imageVector = IconContact,
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                    Column(
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Text(
                            text = "Добавить контакт",
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.W500
                        )
                        Text(text = "Добавить человека в контакты")
                    }
                }
            } else {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Red.copy(0.3f))
                        .clickable { }
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Outlined.ExitToApp,
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                    Column(
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Text(
                            text = "Выйти из аккаунта",
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.W500
                        )
                    }
                }
            }
        }
    }
}