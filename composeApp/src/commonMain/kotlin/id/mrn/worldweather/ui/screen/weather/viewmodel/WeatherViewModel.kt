package id.mrn.worldweather.ui.screen.weather.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.mrn.services.base.Result
import id.mrn.services.domain.SearchUseCase
import id.mrn.worldweather.ui.screen.weather.uistate.WeatherUiState
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class WeatherViewModel(
    private val searchUseCase: SearchUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(WeatherUiState())
    val uiState = _uiState.asStateFlow()

//    private val _itemPaging =
//        MutableStateFlow<PagingData<Example>>(PagingData.empty())
//    val itemPaging = _itemPaging.asStateFlow()

//    fun getCallback(): WeatherCallback = WeatherCallback(

//    )

    fun init() {
        search("tang")
    }

    fun search(query: String) {
        searchUseCase(query).onEach { result ->
            when (result) {
                is Result.Success -> {
                    _uiState.value = _uiState.value.copy(
                        searchResults = result.data
                    )
                    println("Hasil pencarian: ${result.data}")
                }

                is Result.Error -> {}
            }
        }.launchIn(viewModelScope)
    }

    fun onClear() {
        viewModelScope.cancel()
        println("WeatherViewModel dibersihkan!")
    }
}