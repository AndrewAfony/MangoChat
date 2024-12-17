package andrewafony.testapp.mangotestchat

import andrewafony.testapp.designsystem.theme.MangoTestChatTheme
import andrewafony.testapp.mangotestchat.main_screen.MainScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.compose.KoinContext

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModel()

    private val featureProvider: FeatureDestinationProvider by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)

        var mainState by mutableStateOf(MainActivityState())
        var isLoggedIn: Boolean? by mutableStateOf(null)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.state.collectLatest {
                    mainState = it
                }
            }
        }

        splashScreen.setKeepOnScreenCondition {
            when (mainState.screenState) {
                is MainActivityScreenState.Loading -> true
                is MainActivityScreenState.Success -> {
                    isLoggedIn = mainState.isLogged
                    false
                }
            }
        }

        setContent {

            val navController = rememberNavController()

            val backStack by navController.currentBackStackEntryAsState()
            val currentRoute = backStack?.destination?.route

            isLoggedIn?.let {
                KoinContext {
                    MangoTestChatTheme {
                        MainScreen(
                            navController = navController,
                            currentRoute = currentRoute,
                            featureProvider = featureProvider,
                            isLogged = it
                        )
                    }
                }
            }
        }
    }
}