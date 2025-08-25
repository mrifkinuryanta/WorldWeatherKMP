package id.mrn.worldweather.ui.screen.weather.uistate

import id.mrn.services.data.model.SearchEntity

data class WeatherUiState(
    val isLoadingOverlay: Boolean = false,
    val searchResults: List<SearchEntity> = emptyList()
)