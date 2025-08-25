package id.mrn.worldweather.ui.screen.weather.view
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tagsamurai.tscomponents.scaffold.Scaffold
import com.tagsamurai.tscomponents.snackbar.OnShowSnackBar

@Composable
fun WeatherScreen(
    onNavigateUp: () -> Unit,
    onNavigateTo: (String) -> Unit,
    onShowSnackBar: OnShowSnackBar
) {
    val viewModel: WeatherViewModel = hiltViewModel()
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
//    val callback = viewModel.getCallback()

//    LaunchedEffect(Unit) {
//        viewModel.init()
//    }

    WeatherScreen(
        uiState = uiState.value,
//        callback = callback,
        onNavigateUp = onNavigateUp,
        onNavigateTo = onNavigateTo,
        onShowSnackBar = onShowSnackBar
    )
}

@Composable
fun WeatherScreen(
    uiState: WeatherUiState,
//    callback: ExampleCallback,    
    onNavigateUp: () -> Unit,
    onNavigateTo: (String) -> Unit,
    onShowSnackBar: OnShowSnackBar
) {
//    HandleState(
//        state = uiState.deleteState,
//        onShowSnackBar = onShowSnackBar,
//        successMsg = "Success, asset has been deleted.",
//        errorMsg = "Error, failed to delete asset. Please check your connection and try again.",
//        onDispose = homeCallback.onResetMessageState
//    )

    Scaffold(
        isShowLoadingOverlay = uiState.isLoadingOverlay
    ) {
        Column {
            // other content
        }
    }
}