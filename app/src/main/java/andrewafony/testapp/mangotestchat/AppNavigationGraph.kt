package andrewafony.testapp.mangotestchat

import andrewafony.testapp.chat_api.ChatFeatureApi
import andrewafony.testapp.feature_api.register
import andrewafony.testapp.home_api.HomeFeatureApi
import andrewafony.testapp.profile_api.ProfileFeatureApi
import andrewafony.testapp.settings_api.SettingsFeatureApi
import android.provider.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun AppNavigationGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    featureProvider: FeatureDestinationProvider
) {

    val root = remember { featureProvider.provide(HomeFeatureApi::class.java) }

    NavHost(
        navController = navController,
        startDestination = root.route
    ) {

        register(
            featureApi = root,
            navController = navController,
            modifier = modifier
        )

        register(
            featureApi = featureProvider.provide(SettingsFeatureApi::class.java),
            navController = navController,
            modifier = modifier
        )

        register(
            featureApi = featureProvider.provide(ProfileFeatureApi::class.java),
            navController = navController,
            modifier = modifier
        )

        register(
            featureApi = featureProvider.provide(ChatFeatureApi::class.java),
            navController = navController,
            modifier = modifier
        )
    }

}