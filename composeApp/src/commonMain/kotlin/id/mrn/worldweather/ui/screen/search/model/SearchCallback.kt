package id.mrn.worldweather.ui.screen.search.model

import id.mrn.services.data.model.WeatherEntity

data class SearchCallback(
    val onSearch: (query: String) -> Unit = {},
    val onDelete: () -> Unit = {},
    val onToggleSelectAll: () -> Unit = {},
    val onItemSelected: (WeatherEntity.Location) -> Unit = {}
)
