package id.mrn.worldweather.ui.screen.search.uistate

import id.mrn.services.data.model.SearchEntity
import id.mrn.services.data.model.WeatherEntity

data class SearchUiState(
    val isLoading: Boolean = false,
    val allLocations: List<WeatherEntity.Location> = emptyList(),
    val itemsSelected: List<WeatherEntity.Location> = emptyList(),
    val searchResults: List<SearchEntity> = emptyList(),
) {
    val isAllSelected: Boolean
        get() = allLocations.isNotEmpty() && itemsSelected.size == allLocations.size

    val isLocationVisible: Boolean
        get() = searchResults.isEmpty()
}