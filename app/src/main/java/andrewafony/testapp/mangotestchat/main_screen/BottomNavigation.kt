package andrewafony.testapp.mangotestchat.main_screen

import andrewafony.testapp.designsystem.theme.MangoTestChatTheme
import andrewafony.testapp.designsystem.theme.veryLightGray
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

sealed interface BottomNavigationState {

    data object Popup : BottomNavigationState

    data object Home : BottomNavigationState

    data object Settings : BottomNavigationState
}

@Composable
fun BottomNavigation(
    modifier: Modifier = Modifier,
    currentRoute: String?,
    homeRoute: String,
    settingsRoute: String,
    isPopup: Boolean,
    onButtonClick: () -> Unit,
    navigateToHome: () -> Unit,
    navigateToSettings: () -> Unit,
) {

    val state by remember(isPopup, currentRoute) {
        mutableStateOf(
            when {
                isPopup -> BottomNavigationState.Popup
                currentRoute == homeRoute && !isPopup -> BottomNavigationState.Home
                currentRoute == settingsRoute && !isPopup -> BottomNavigationState.Settings
                else -> BottomNavigationState.Popup
            }
        )
    }

    NavigationBar(
        modifier = modifier
            .fillMaxWidth()
            .drawBehind {
                drawLine(
                    color = Color.LightGray,
                    start = Offset(0f, 0f),
                    end = Offset(size.width, 0f)
                )
            }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surface),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {

            NavigationBarItem(
                selected = currentRoute == homeRoute,
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.6f)
                ),
                onClick = {
                    if (currentRoute != homeRoute) {
                        navigateToHome()
                    }
                },
                icon = {
                    Icon(
                        imageVector = Icons.Outlined.Home,
                        contentDescription = null,
                        modifier = Modifier
                            .size(32.dp)
                    )
                }
            )

            AnimatedContent(
                modifier = Modifier.weight(2f),
                targetState = state,
                transitionSpec = {
                    scaleIn(initialScale = 0.9f) + fadeIn() togetherWith
                            scaleOut(targetScale = 0.9f) + fadeOut()
                }
            ) { targetState ->
                Row(
                    modifier = Modifier
                        .clip(CircleShape)
                        .clickable { onButtonClick() }
                        .background(Color.Black),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp, alignment = Alignment.CenterHorizontally)
                ) {

                    when (targetState) {
                        is BottomNavigationState.Popup -> {
                            Text(
                                modifier = Modifier
                                    .padding(vertical = 10.dp),
                                text = "Закрыть",
                                style = MaterialTheme.typography.bodyLarge,
                                color = Color.White,
                            )
                        }

                        is BottomNavigationState.Home -> {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = null,
                                tint = Color.White
                            )
                            Text(
                                modifier = Modifier.padding(vertical = 10.dp),
                                text = "Новый чат",
                                style = MaterialTheme.typography.bodyLarge,
                                color = Color.White
                            )
                        }

                        is BottomNavigationState.Settings -> {
                            Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = null,
                                tint = Color.White
                            )
                            Text(
                                modifier = Modifier.padding(vertical = 10.dp),
                                text = "Меню",
                                style = MaterialTheme.typography.bodyLarge,
                                color = Color.White
                            )
                        }
                    }
                }
            }

            NavigationBarItem(
                selected = currentRoute == settingsRoute,
                onClick = {
                    if (currentRoute != settingsRoute) {
                        navigateToSettings()
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.6f)
                ),
                icon = {
                    Icon(
                        imageVector = Icons.Outlined.Settings,
                        contentDescription = null,
                        modifier = Modifier.size(32.dp)
                    )
                }
            )
        }
    }
}

@Preview
@Composable
private fun BottomNavigationPrev() {
    MangoTestChatTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(veryLightGray),
            contentAlignment = Alignment.BottomCenter
        ) {
            BottomNavigation(
                currentRoute = "home",
                homeRoute = "home",
                settingsRoute = "settings",
                onButtonClick = { },
                isPopup = false,
                navigateToSettings = {},
                navigateToHome = {}
            )
        }
    }
}