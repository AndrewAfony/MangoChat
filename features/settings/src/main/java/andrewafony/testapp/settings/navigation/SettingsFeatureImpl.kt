package andrewafony.testapp.settings.navigation

import andrewafony.testapp.profile_api.ProfileFeatureApi
import andrewafony.testapp.settings.SettingsScreen
import andrewafony.testapp.settings_api.SettingsFeatureApi
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable

private const val settingsRoute = "settings"

class SettingsFeatureImpl internal constructor(
    private val profile: ProfileFeatureApi
): SettingsFeatureApi {

    override val route: String = settingsRoute

    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        modifier: Modifier
    ) {
        navGraphBuilder.composable(
            route = settingsRoute
        ) {
            SettingsScreen(
                modifier = modifier,
                navigateToProfile = { navController.navigate(profile.route) }
            )
        }
    }
}