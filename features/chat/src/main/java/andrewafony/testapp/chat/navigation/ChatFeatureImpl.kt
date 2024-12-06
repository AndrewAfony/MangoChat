package andrewafony.testapp.chat.navigation

import andrewafony.testapp.chat.ChatScreen
import andrewafony.testapp.chat_api.ChatFeatureApi
import androidx.compose.ui.Modifier
import androidx.navigation.compose.composable

private const val chatRoute = "chat"

class ChatFeatureImpl: ChatFeatureApi {

    override val route: String = chatRoute

    override fun registerGraph(
        navGraphBuilder: androidx.navigation.NavGraphBuilder,
        navController: androidx.navigation.NavHostController,
        modifier: Modifier,
    ) {
        navGraphBuilder.composable(chatRoute) {
            ChatScreen(modifier = modifier)
        }
    }
}