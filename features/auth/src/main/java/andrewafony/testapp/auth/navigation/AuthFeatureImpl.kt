package andrewafony.testapp.auth.navigation

import andrewafony.testapp.auth.login.RegistrationScreen
import andrewafony.testapp.auth.registration.LoginScreen
import andrewafony.testapp.auth_api.AuthFeatureApi
import andrewafony.testapp.home_api.HomeFeatureApi
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable

private const val authRoute = "auth"
private const val registrationRoute = "registration"

class AuthFeatureImpl(
    private val homeFeature: HomeFeatureApi
): AuthFeatureApi {

    override val route: String = registrationRoute

    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        modifier: Modifier
    ) {
        navGraphBuilder.composable(
            route = registrationRoute
        ) {
            RegistrationScreen(
                modifier = modifier,
                navigateToHome = { navController.navigate(homeFeature.route) {
                    popUpTo(homeFeature.route) { inclusive = true }
                } }
            )
        }

        navGraphBuilder.composable(
            route = authRoute
        ) {
            LoginScreen(
                modifier = modifier,
                navigateToHome = { navController.navigate(homeFeature.route) {
                    popUpTo(homeFeature.route) { inclusive = true }
                } },
                navigateBack = { navController.popBackStack() }
            )
        }
    }
}