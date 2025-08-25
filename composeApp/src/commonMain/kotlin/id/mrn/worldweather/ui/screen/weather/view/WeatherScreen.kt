package id.mrn.worldweather.ui.screen.weather.view

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import id.mrn.worldweather.ui.component.Scaffold
import id.mrn.worldweather.ui.screen.weather.uistate.WeatherUiState
import id.mrn.worldweather.ui.screen.weather.viewmodel.WeatherViewModel
import org.koin.compose.koinInject

@Composable
fun WeatherScreen(
    onNavigateUp: () -> Unit,
    onNavigateTo: (String) -> Unit,
) {
    val viewModel: WeatherViewModel = koinInject()
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
//    val callback = viewModel.getCallback()

    DisposableEffect(Unit) {
        viewModel.init()

        onDispose {
            viewModel.onClear()
        }
    }

    WeatherScreen(
        uiState = uiState.value,
//        callback = callback,
        onNavigateUp = onNavigateUp,
        onNavigateTo = onNavigateTo
    )
}

@Composable
fun WeatherScreen(
    uiState: WeatherUiState,
//    callback: ExampleCallback,
    onNavigateUp: () -> Unit,
    onNavigateTo: (String) -> Unit
) {
//    HandleState(
//        state = uiState.deleteState,
//        onShowSnackBar = onShowSnackBar,
//        successMsg = "Success, asset has been deleted.",
//        errorMsg = "Error, failed to delete asset. Please check your connection and try again.",
//        onDispose = homeCallback.onResetMessageState
//    )

    Scaffold {
        Column {
            Text(
                text = "Weather Screen",
                style = MaterialTheme.typography.headlineLarge,
                color = Color.Black
            )
            Text(
                text = "Search Results: ${uiState.searchResults.size}",
                style = MaterialTheme.typography.titleMedium,
                color = Color.Black
            )
            uiState.searchResults.forEach { value ->
                Text(
                    text = value.name,
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.Black
                )
            }
        }
    }
}