package andrewafony.testapp.mangotestchat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import andrewafony.testapp.mangotestchat.theme.theme.MangoTestChatTheme
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MangoTestChatTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    ChatApp()
                }
            }
        }
    }
}

@Composable
fun ChatApp() {

}