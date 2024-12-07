package andrewafony.testapp.auth.navigation

import andrewafony.testapp.auth.login.LoginScreen
import andrewafony.testapp.auth.registration.RegistrationScreen
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

    override val route: String = authRoute

    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        modifier: Modifier,
    ) {
        navGraphBuilder.composable(authRoute) {
            LoginScreen(
                modifier = modifier,
                navigateToHome = { navController.navigate(homeFeature.route) },
                navigateToRegistration = { navController.navigate(registrationRoute) }
            )
        }

        navGraphBuilder.composable(registrationRoute) {
            RegistrationScreen(
                modifier = modifier
            )
        }
    }
}