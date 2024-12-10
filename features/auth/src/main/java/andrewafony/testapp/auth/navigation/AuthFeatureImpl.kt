package andrewafony.testapp.auth.navigation

import andrewafony.testapp.auth.login.LoginScreen
import andrewafony.testapp.auth.registration.RegistrationScreen
import andrewafony.testapp.auth_api.AuthFeatureApi
import andrewafony.testapp.home_api.HomeFeatureApi
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

private const val authRoute = "auth"
private const val registrationRoute = "registration"

private const val phoneArg = "phone_arg"

class AuthFeatureImpl(
    private val homeFeature: HomeFeatureApi,
) : AuthFeatureApi {

    override val route: String = authRoute

    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        modifier: Modifier,
    ) {

        navGraphBuilder.composable(
            route = "$registrationRoute/{$phoneArg}",
            arguments = listOf(navArgument(phoneArg) { type = NavType.StringType })
        ) { backStackEntry ->
            RegistrationScreen(
                modifier = modifier,
                number = backStackEntry.arguments?.getString(phoneArg) ?: "",
                navigateBack = { navController.popBackStack() },
                navigateToHome = {
                    navController.navigate(homeFeature.route) {
                        popUpTo(authRoute) { inclusive = true }
                    }
                }
            )
        }

        navGraphBuilder.composable(
            route = authRoute
        ) {
            LoginScreen(
                modifier = modifier,
                navigateToRegistration = { phone ->
                    navController.navigate("$registrationRoute/$phone")
                },
                navigateToHome = {
                    navController.navigate(homeFeature.route) {
                        popUpTo(authRoute) { inclusive = true }
                    }
                }
            )
        }
    }
}