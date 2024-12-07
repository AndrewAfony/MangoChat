package andrewafony.testapp.feature_api

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.navigation.NavBackStackEntry
import org.koin.androidx.compose.koinViewModel

@Composable
inline fun <reified T : ViewModel> NavBackStackEntry?.viewModel(): T {
    return if (this == null)
        koinViewModel()
    else
        koinViewModel(viewModelStoreOwner = this)
}

@Composable
inline fun <reified T : ViewModel> NavBackStackEntry.viewModel(
    viewModelStoreOwner: ViewModelStoreOwner = checkNotNull(LocalViewModelStoreOwner.current) {
        "No ViewModelStoreOwner was provided via LocalViewModelStoreOwner"
    },
): T {
    return koinViewModel(
        viewModelStoreOwner = viewModelStoreOwner, key = T::class.java.name
    )
}