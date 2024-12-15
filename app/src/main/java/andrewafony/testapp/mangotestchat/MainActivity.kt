package andrewafony.testapp.mangotestchat

import andrewafony.testapp.designsystem.theme.MangoTestChatTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModel()

    private val featureProvider: FeatureDestinationProvider by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val isLogged = viewModel.isLogged()

        setContent {

            val navController = rememberNavController()

            val backStack by navController.currentBackStackEntryAsState()
            val currentRoute = backStack?.destination?.route

            MangoTestChatTheme {
                MainScreen(
                    navController = navController,
                    currentRoute = currentRoute,
                    featureProvider = featureProvider,
                    isLogged = isLogged
                )
            }
        }
    }
}

