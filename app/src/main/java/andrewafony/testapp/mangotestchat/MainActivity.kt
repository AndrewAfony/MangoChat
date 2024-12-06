package andrewafony.testapp.mangotestchat

import andrewafony.testapp.designsystem.theme.MangoTestChatTheme
import andrewafony.testapp.home_api.HomeFeatureApi
import andrewafony.testapp.profile_api.ProfileFeatureApi
import andrewafony.testapp.settings_api.SettingsFeatureApi
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import org.koin.android.ext.android.get

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val homeFeature: HomeFeatureApi = get()
        val settingsFeature: SettingsFeatureApi = get()
        val profileFeature: ProfileFeatureApi = get()

        setContent {

            val navController = rememberNavController()

            val backStack by navController.currentBackStackEntryAsState()
            val currentRoute = backStack?.destination?.route

            MangoTestChatTheme {
                Scaffold(
                    bottomBar = {
                        if (currentRoute in listOf("home", "settings"))
                            BottomNavigation(
                                currentRoute = currentRoute ?: "",
                                navigateToHome = { navController.navigate(homeFeature.route) {
                                    popUpTo(homeFeature.route) { inclusive = true}
                                } },
                                navigateToProfile = { navController.navigate(settingsFeature.route) {
                                    popUpTo(settingsFeature.route) { inclusive = true}
                                } }
                            )
                    },
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    AppNavigationGraph(
                        modifier = Modifier.padding(innerPadding),
                        navController = navController,
                        homeFeatureImpl = homeFeature,
                        settingsFeatureImpl = settingsFeature,
                        profileFeatureImpl = profileFeature
                    )
                }
            }
        }
    }
}

@Composable
fun BottomNavigation(
    modifier: Modifier = Modifier,
    currentRoute: String,
    navigateToHome: () -> Unit,
    navigateToProfile: () -> Unit,
) {

    // TODO check click through

    NavigationBar(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surface),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {

            NavigationBarItem(
                selected = currentRoute == "home",
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.6f)
                ),
                onClick = navigateToHome,
                icon = {
                    Icon(
                        imageVector = Icons.Outlined.Home,
                        contentDescription = "",
                        modifier = Modifier
                            .size(32.dp)
                    )
                }
            )

            Row(
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .clip(CircleShape)
                    .clickable { }
                    .weight(2f)
                    .background(Color.Black),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null,
                    tint = Color.White
                )
                Spacer(modifier = Modifier.padding(4.dp))
                Text(
                    modifier = Modifier.padding(vertical = 12.dp),
                    text = "New chat",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.White
                )
            }

            NavigationBarItem(
                selected = currentRoute == "settings",
                onClick = navigateToProfile,
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.6f)
                ),
                icon = {
                    Icon(
                        imageVector = Icons.Outlined.Settings,
                        contentDescription = "",
                        modifier = Modifier.size(32.dp)
                    )
                }
            )
        }
    }
}

@Preview
@Composable
private fun MainActivityPrev() {
    MangoTestChatTheme {
        BottomNavigation(
            navigateToProfile = {},
            navigateToHome = {},
            currentRoute = "home"
        )
    }
}

