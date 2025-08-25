package id.mrn.worldweather.ui.screen.weather.viewmodel
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(): ViewModel() {
    private val _uiState = MutableStateFlow(WeatherUiState())
    val uiState = _uiState.asStateFlow()
    
//    private val _itemPaging =
//        MutableStateFlow<PagingData<Example>>(PagingData.empty())
//    val itemPaging = _itemPaging.asStateFlow()

//    fun getCallback(): WeatherCallback = WeatherCallback(
        
//    )
}