package id.mrn.worldweather.ui.screen.weather.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.mrn.services.SpaceXSDK
import id.mrn.worldweather.ui.screen.weather.uistate.WeatherUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class WeatherViewModel(
    private val sdk: SpaceXSDK
) : ViewModel() {
    private val _uiState = MutableStateFlow(WeatherUiState())
    val uiState = _uiState.asStateFlow()

    init {
        loadLaunches()
    }

    fun loadLaunches() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, launches = emptyList())
            try {
                val launches = sdk.getLaunches(forceReload = true)
                _uiState.value = _uiState.value.copy(isLoading = false, launches = launches)
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(isLoading = false, launches = emptyList())
            }
        }
    }
}