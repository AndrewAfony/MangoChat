package andrewafony.testapp.shared_ui.utils

import android.app.Activity
import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext

fun Context.toast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}

@Composable
fun SetWindowSoftInputMode(
    mode: Int
) {
    val context = LocalContext.current
    LaunchedEffect(mode) {
        val activity = context as? Activity ?: return@LaunchedEffect
        activity.window.setSoftInputMode(mode)
    }
}