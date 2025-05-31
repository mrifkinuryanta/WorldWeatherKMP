package id.mrn.worldweather

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import id.mrn.worldweather.ui.screen.weather.view.WeatherScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        WeatherScreen(
            onNavigateUp = {},
            onNavigateTo = {}
        )
    }
}