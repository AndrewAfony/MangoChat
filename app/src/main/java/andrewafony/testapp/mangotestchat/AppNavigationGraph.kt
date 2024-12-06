package andrewafony.testapp.mangotestchat

import andrewafony.testapp.feature_api.register
import andrewafony.testapp.home_api.HomeFeatureApi
import andrewafony.testapp.profile_api.ProfileFeatureApi
import andrewafony.testapp.settings_api.SettingsFeatureApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun AppNavigationGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    homeFeatureImpl: HomeFeatureApi,
    settingsFeatureImpl: SettingsFeatureApi,
    profileFeatureImpl: ProfileFeatureApi,
) {

    NavHost(
        navController = navController,
        startDestination = homeFeatureImpl.route
    ) {

        register(
            featureApi = homeFeatureImpl,
            navController = navController,
            modifier = modifier
        )

        register(
            featureApi = settingsFeatureImpl,
            navController = navController,
            modifier = modifier
        )

        register(
            featureApi = profileFeatureImpl,
            navController = navController,
            modifier = modifier
        )
    }

}