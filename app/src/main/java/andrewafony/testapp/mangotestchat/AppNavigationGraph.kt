package andrewafony.testapp.mangotestchat

import andrewafony.testapp.auth_api.AuthFeatureApi
import andrewafony.testapp.chat_api.ChatFeatureApi
import andrewafony.testapp.designsystem.animation.enterPop
import andrewafony.testapp.designsystem.animation.enterPush
import andrewafony.testapp.designsystem.animation.exitPop
import andrewafony.testapp.designsystem.animation.exitPush
import andrewafony.testapp.feature_api.register
import andrewafony.testapp.home_api.HomeFeatureApi
import andrewafony.testapp.profile_api.ProfileFeatureApi
import andrewafony.testapp.settings_api.SettingsFeatureApi
import androidx.compose.animation.EnterTransition
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun AppNavigationGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    currentRoute: String?,
    featureProvider: FeatureDestinationProvider,
) {

    val root = remember { featureProvider.provide(HomeFeatureApi::class.java) }

    NavHost(
        navController = navController,
        startDestination = featureProvider.provide(AuthFeatureApi::class.java).route,
        enterTransition = {
            if (currentRoute == "home" || currentRoute == "settings") {
                EnterTransition.None
            } else
                enterPush()
        },
        exitTransition = { exitPush() },
        popEnterTransition = { enterPop() },
        popExitTransition = { exitPop() }
    ) {

        register(
            featureApi = featureProvider.provide(AuthFeatureApi::class.java),
            navController = navController,
            modifier = modifier
        )

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

