package id.mrn.worldweather.ui.screen.weather.uistate

import id.mrn.services.data.model.RocketLaunch

data class WeatherUiState(
    val isLoading: Boolean = false,
    val launches: List<RocketLaunch> = emptyList()
)