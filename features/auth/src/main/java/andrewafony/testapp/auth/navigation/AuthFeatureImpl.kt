package andrewafony.testapp.auth.navigation

import andrewafony.testapp.auth.login.LoginScreen
import andrewafony.testapp.auth_api.AuthFeatureApi
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable

private const val authRoute = "auth"

class AuthFeatureImpl: AuthFeatureApi {

    override val route: String = authRoute

    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        modifier: Modifier,
    ) {
        navGraphBuilder.composable(authRoute) {
            LoginScreen()
        }
    }
}