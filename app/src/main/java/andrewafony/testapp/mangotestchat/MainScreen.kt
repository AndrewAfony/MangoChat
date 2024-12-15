package andrewafony.testapp.mangotestchat

import andrewafony.testapp.chat_api.ChatFeatureApi
import andrewafony.testapp.designsystem.theme.MangoTestChatTheme
import andrewafony.testapp.home_api.HomeFeatureApi
import andrewafony.testapp.settings_api.SettingsFeatureApi
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun MainScreen(
    navController: NavHostController,
    currentRoute: String?,
    featureProvider: FeatureDestinationProvider,
    isLogged: Boolean
) {

    val homeFeatureRoute = remember { featureProvider.provide(HomeFeatureApi::class.java).route }
    val settingsFeatureRoute = remember { featureProvider.provide(SettingsFeatureApi::class.java).route }
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
}


@Composable
fun BottomNavigation(
    modifier: Modifier = Modifier,
    currentRoute: String?,
    homeRoute: String,
    settingsRoute: String,
    navigateToHome: () -> Unit,
    navigateToSettings: () -> Unit,
) {

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
                onClick = { if (currentRoute != homeRoute) navigateToHome() },
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
                targetState = currentRoute == homeRoute,
                transitionSpec = {
                    scaleIn(initialScale = 0.9f) + fadeIn() togetherWith
                    scaleOut(targetScale = 0.9f) + fadeOut()
                }
            ) { targetState ->

                Row(
                    modifier = Modifier
                        .clip(CircleShape)
                        .clickable { }
                        .background(Color.Black),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    if (targetState) {

                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = null,
                            tint = Color.White
                        )
                        Spacer(modifier = Modifier.padding(4.dp))
                        Text(
                            modifier = Modifier.padding(vertical = 10.dp),
                            text = "New chat",
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color.White
                        )

                    } else {

                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = null,
                            tint = Color.White
                        )
                        Spacer(modifier = Modifier.padding(4.dp))
                        Text(
                            modifier = Modifier.padding(vertical = 10.dp),
                            text = "Open menu",
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color.White
                        )
                    }
                }
            }

            NavigationBarItem(
                selected = currentRoute == settingsRoute,
                onClick = { if (currentRoute != settingsRoute) navigateToSettings() },
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
        BottomNavigation(
            currentRoute = "home",
            homeRoute = "home",
            settingsRoute = "settings",
            navigateToSettings = {},
            navigateToHome = {}
        )
    }
}