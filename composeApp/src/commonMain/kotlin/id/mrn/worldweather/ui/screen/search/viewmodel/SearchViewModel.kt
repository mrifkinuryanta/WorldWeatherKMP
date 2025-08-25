package id.mrn.worldweather.ui.screen.search.viewmodel
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(): ViewModel() {
    private val _uiState = MutableStateFlow(SearchUiState())
    val uiState = _uiState.asStateFlow()
    
//    private val _itemPaging =
//        MutableStateFlow<PagingData<Example>>(PagingData.empty())
//    val itemPaging = _itemPaging.asStateFlow()

//    fun getCallback(): SearchCallback = SearchCallback(
        
//    )
}