package andrewafony.testapp.mangotestchat.main_screen

import andrewafony.testapp.chat_api.ChatFeatureApi
import andrewafony.testapp.home_api.HomeFeatureApi
import andrewafony.testapp.mangotestchat.AppNavigationGraph
import andrewafony.testapp.mangotestchat.FeatureDestinationProvider
import andrewafony.testapp.settings_api.SettingsFeatureApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
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