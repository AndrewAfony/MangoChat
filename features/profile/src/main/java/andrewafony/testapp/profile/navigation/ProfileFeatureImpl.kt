package andrewafony.testapp.profile.navigation

import andrewafony.testapp.feature_api.viewModel
import andrewafony.testapp.profile.ProfileViewModel
import andrewafony.testapp.profile.screen.EditCityScreen
import andrewafony.testapp.profile.screen.EditNameScreen
import andrewafony.testapp.profile.screen.ProfileScreen
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
        navGraphBuilder.composable(route = profileRoute) {
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

        navGraphBuilder.composable(route = profileNameEditRoute) {
            val profileViewModel: ProfileViewModel = navController.previousBackStackEntry.viewModel()
            EditNameScreen(
                modifier = modifier,
                viewModel = profileViewModel,
                navigateBack = { navController.popBackStack() }
            )
        }

        navGraphBuilder.composable(route = profileCityEditRoute) {
            val profileViewModel: ProfileViewModel = navController.previousBackStackEntry.viewModel()
            EditCityScreen(
                viewModel = profileViewModel,
                navigateBack = { navController.popBackStack() }
            )
        }
    }
}