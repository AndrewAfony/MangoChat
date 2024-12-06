package andrewafony.testapp.profile.navigation

import andrewafony.testapp.profile.EditCityScreen
import andrewafony.testapp.profile.EditNameScreen
import andrewafony.testapp.profile.ProfileScreen
import andrewafony.testapp.profile_api.ProfileFeatureApi
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable

private const val profileRoute = "profile"
private const val profileNameEditRoute = "profile_edit_name"
private const val profileCityEditRoute = "profile_edit_city"

class ProfileFeatureImpl internal constructor() : ProfileFeatureApi {

    override val route: String = profileRoute

    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        modifier: Modifier,
    ) {
        navGraphBuilder.composable(profileRoute) {
            ProfileScreen(
                modifier = modifier,
                navigateToNameEdit = {
                    navController.navigate(profileNameEditRoute)
                },
                navigateToCityEdit = {
                    navController.navigate(profileCityEditRoute)
                },
                navigateBack = { navController.popBackStack() }
            )
        }

        navGraphBuilder.composable(profileNameEditRoute) {
            EditNameScreen(
                modifier = modifier,
                navigateBack = { navController.popBackStack() }
            )
        }

        navGraphBuilder.composable(profileCityEditRoute) {
            EditCityScreen(
                navigateBack = { navController.popBackStack() }
            )
        }
    }
}