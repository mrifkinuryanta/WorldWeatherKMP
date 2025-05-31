package id.mrn.worldweather.ui.screen.weather.view

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import id.mrn.worldweather.ui.screen.weather.uistate.WeatherUiState
import id.mrn.worldweather.ui.screen.weather.viewmodel.WeatherViewModel
import org.koin.compose.koinInject

@Composable
fun WeatherScreen(
    onNavigateUp: () -> Unit,
    onNavigateTo: (String) -> Unit
) {
    val viewModel: WeatherViewModel = koinInject()
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

//    LaunchedEffect(Unit) {
//        viewModel.init()
//    }

    WeatherScreen(
        uiState = uiState.value,
        onNavigateUp = onNavigateUp,
        onNavigateTo = onNavigateTo
    )
}

@Composable
fun WeatherScreen(
    uiState: WeatherUiState,
    onNavigateUp: () -> Unit,
    onNavigateTo: (String) -> Unit
) {
    LazyColumn {
        items(uiState.launches.size) { index ->
            val launch = uiState.launches[index]
            Text(
                text = "${launch.flightNumber}: ${launch.missionName}",
                style = MaterialTheme.typography.headlineMedium
            )
        }
    }
}