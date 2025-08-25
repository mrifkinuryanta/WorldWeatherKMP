package id.mrn.worldweather.ui.screen.search.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.mrn.services.base.Result
import id.mrn.services.data.model.WeatherEntity
import id.mrn.services.domain.DeleteLocationsUseCase
import id.mrn.services.domain.GetAllLocationsUseCase
import id.mrn.services.domain.SearchUseCase
import id.mrn.worldweather.ui.screen.search.model.SearchCallback
import id.mrn.worldweather.ui.screen.search.uistate.SearchUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class SearchViewModel(
    private val getAllLocationsUseCase: GetAllLocationsUseCase,
    private val searchUseCase: SearchUseCase,
    private val deleteLocationsUseCase: DeleteLocationsUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(SearchUiState())
    val uiState = _uiState.asStateFlow()

//    private val _itemPaging =
//        MutableStateFlow<PagingData<Example>>(PagingData.empty())
//    val itemPaging = _itemPaging.asStateFlow()

    fun getCallback(): SearchCallback = SearchCallback(
        onSearch = ::onSearch,
        onDelete = ::onDelete,
        onToggleSelectAll = ::onToggleSelectAll,
        onItemSelected = ::onItemSelected
    )

    fun init() {
        getAllLocations()
    }

    fun onSearch(query: String) {
        _uiState.value = _uiState.value.copy(isLoading = true)

        searchUseCase(query).onEach { result ->
            when (result) {
                is Result.Success -> {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        searchResults = result.data
                    )
                    println(result.data)
                }

                is Result.Error -> {
                    _uiState.value = _uiState.value.copy(isLoading = false)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun onDelete() {
        val ids = _uiState.value.itemsSelected.map { it.id }

        deleteLocationsUseCase(locationsIds = ids).onEach { result ->
            when (result) {
                is Result.Success -> {
                    println("Deleted locations: ${result.data}")
                }

                is Result.Error -> {
                    println("Error deleting locations: ${result.message}")
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getAllLocations() {
        getAllLocationsUseCase().onEach { result ->
            if (result is Result.Success) {
                _uiState.value = _uiState.value.copy(allLocations = result.data)
            }
        }.launchIn(viewModelScope)
    }

    fun onToggleSelectAll() {
        val itemsSelected = _uiState.value.itemsSelected.toMutableList()

        if (_uiState.value.isAllSelected) {
            itemsSelected.clear()
        } else {
            itemsSelected.addAll(_uiState.value.allLocations)
        }

        _uiState.value = _uiState.value.copy(itemsSelected = itemsSelected)
    }

    fun onItemSelected(item: WeatherEntity.Location) {
        val itemsSelected = _uiState.value.itemsSelected.toMutableList()
        if (itemsSelected.contains(item)) {
            itemsSelected.remove(item)
        } else {
            itemsSelected.add(item)
        }
        _uiState.value = _uiState.value.copy(itemsSelected = itemsSelected)
    }

    fun onClear() {
        _uiState.value = SearchUiState()
    }
}