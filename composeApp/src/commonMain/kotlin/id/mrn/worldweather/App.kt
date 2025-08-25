package id.mrn.worldweather

import androidx.compose.runtime.Composable
import id.mrn.worldweather.ui.screen.search.view.SearchScreen
import id.mrn.worldweather.ui.screen.weather.view.WeatherScreen
import id.mrn.worldweather.ui.theme.AppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    AppTheme {
        SearchScreen(
            onNavigateUp = {},
            onNavigateTo = {}
        )
    }
}