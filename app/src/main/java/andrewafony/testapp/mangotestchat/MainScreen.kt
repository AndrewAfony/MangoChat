package andrewafony.testapp.mangotestchat

import andrewafony.testapp.chat_api.ChatFeatureApi
import andrewafony.testapp.designsystem.icons.ChatIcon
import andrewafony.testapp.designsystem.icons.IconContact
import andrewafony.testapp.designsystem.theme.MangoTestChatTheme
import andrewafony.testapp.designsystem.theme.lightGray
import andrewafony.testapp.designsystem.theme.veryLightGray
import andrewafony.testapp.home_api.HomeFeatureApi
import andrewafony.testapp.settings_api.SettingsFeatureApi
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ExitToApp
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun MainScreen(
    navController: NavHostController,
    currentRoute: String?,
    featureProvider: FeatureDestinationProvider,
    isLogged: Boolean,
) {

    var isPopup by remember { mutableStateOf(false) }

    val animatedAlpha by animateFloatAsState(
        targetValue = if (isPopup) 0.8f else 0f
    )

    val homeFeatureRoute = remember { featureProvider.provide(HomeFeatureApi::class.java).route }
    val settingsFeatureRoute =
        remember { featureProvider.provide(SettingsFeatureApi::class.java).route }
    val chatFeatureRoute = remember { featureProvider.provide(ChatFeatureApi::class.java).route }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            AnimatedVisibility(
                visible = currentRoute in featureProvider.topRoutes,
                enter = slideInVertically(
                    animationSpec = tween(500),
                    initialOffsetY = { it }),
                exit = if (currentRoute == chatFeatureRoute) {
                    slideOutHorizontally(
                        animationSpec = tween(500),
                        targetOffsetX = { -it })
                } else
                    slideOutVertically(
                        animationSpec = tween(300),
                        targetOffsetY = { it })
            ) {
                BottomNavigation(
                    currentRoute = currentRoute,
                    homeRoute = homeFeatureRoute,
                    settingsRoute = settingsFeatureRoute,
                    onButtonClick = { isPopup = !isPopup },
                    isPopup = isPopup,
                    navigateToHome = {
                        navController.navigate(homeFeatureRoute) {
                            popUpTo(homeFeatureRoute) { inclusive = true }
                        }
                    },
                    navigateToSettings = {
                        navController.navigate(settingsFeatureRoute) {
                            popUpTo(settingsFeatureRoute) { inclusive = true }
                        }
                    }
                )
            }
        }
    ) { _ ->
        AppNavigationGraph(
            navController = navController,
            featureProvider = featureProvider,
            currentRoute = currentRoute,
            isLogged = isLogged
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 80.dp)
            .background(lightGray.copy(animatedAlpha))
            .then(if (isPopup) {
                Modifier
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ) { isPopup = false }
            } else {
                Modifier
            })
    ) {}

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
                isCurrentHomeRoute = currentRoute == homeFeatureRoute
            )
        }
    }

    BackHandler(
        enabled = isPopup
    ) {
        isPopup = false
    }
}

sealed interface BottomNavState {

    data object Popup : BottomNavState

    data object Home : BottomNavState

    data object Settings : BottomNavState
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
                isPopup -> BottomNavState.Popup
                currentRoute == homeRoute && !isPopup -> BottomNavState.Home
                currentRoute == settingsRoute && !isPopup -> BottomNavState.Settings
                else -> BottomNavState.Popup
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
                    horizontalArrangement = Arrangement.Center
                ) {

                    when (targetState) {
                        is BottomNavState.Popup -> {
                            Text(
                                modifier = Modifier
                                    .padding(vertical = 10.dp),
                                text = "Закрыть",
                                style = MaterialTheme.typography.bodyLarge,
                                color = Color.White,
                            )
                        }

                        is BottomNavState.Home -> {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = null,
                                tint = Color.White
                            )
                            Spacer(modifier = Modifier.padding(4.dp))
                            Text(
                                modifier = Modifier.padding(vertical = 10.dp),
                                text = "Новый чат",
                                style = MaterialTheme.typography.bodyLarge,
                                color = Color.White
                            )
                        }

                        is BottomNavState.Settings -> {
                            Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = null,
                                tint = Color.White
                            )
                            Spacer(modifier = Modifier.padding(4.dp))
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