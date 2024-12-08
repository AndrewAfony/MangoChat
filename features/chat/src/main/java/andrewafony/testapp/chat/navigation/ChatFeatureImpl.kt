package andrewafony.testapp.chat.navigation

import andrewafony.testapp.chat.ChatScreen
import andrewafony.testapp.chat_api.ChatFeatureApi
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable

private const val chatRoute = "chat"

class ChatFeatureImpl: ChatFeatureApi {

    override val route: String = chatRoute

    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        modifier: Modifier,
    ) {
        navGraphBuilder.composable(
            route = chatRoute
        ) {
            ChatScreen(
                modifier = modifier,
                navigateBack = { navController.popBackStack() }
            )
        }
    }
}