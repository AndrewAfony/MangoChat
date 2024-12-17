package andrewafony.testapp.mangotestchat.main_screen

import andrewafony.testapp.chat_api.ChatFeatureApi
import andrewafony.testapp.designsystem.icons.ChatIcon
import andrewafony.testapp.designsystem.icons.IconContact
import andrewafony.testapp.designsystem.theme.MangoTestChatTheme
import andrewafony.testapp.designsystem.theme.lightGray
import andrewafony.testapp.designsystem.theme.veryLightGray
import andrewafony.testapp.home_api.HomeFeatureApi
import andrewafony.testapp.mangotestchat.AppNavigationGraph
import andrewafony.testapp.mangotestchat.FeatureDestinationProvider
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
import androidx.compose.ui.graphics.graphicsLayer
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
                    animationSpec = tween(300),
                    initialOffsetY = { it }),
                exit = if (currentRoute == chatFeatureRoute) {
                    slideOutHorizontally(
                        animationSpec = tween(300),
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

    BottomNavigationButtonPopup(
        isPopup = isPopup,
        isCurrentHomeRoute = currentRoute == homeFeatureRoute,
        onPopupChange = { isPopup = it }
    )
}