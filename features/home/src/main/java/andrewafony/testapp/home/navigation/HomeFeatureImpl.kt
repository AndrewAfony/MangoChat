package andrewafony.testapp.home.navigation

import andrewafony.testapp.home.HomeScreen
import andrewafony.testapp.home_api.HomeFeatureApi
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable

private const val homeRoute = "home"

class HomeFeatureImpl internal constructor(): HomeFeatureApi {

    override val route: String = homeRoute

    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        modifier: Modifier,
    ) {
        navGraphBuilder.composable(homeRoute) {
            HomeScreen(
                modifier = modifier,
            )
        }

    }
}