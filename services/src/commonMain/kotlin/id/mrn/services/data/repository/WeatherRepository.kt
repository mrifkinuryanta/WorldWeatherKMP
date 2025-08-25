package id.mrn.services.data.repository

import id.mrn.services.base.Result
import id.mrn.services.data.model.SearchEntity
import id.mrn.services.data.model.WeatherEntity
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    fun getAllLocations(): Flow<Result<List<WeatherEntity.Location>>>
    fun deleteLocations(locationsIds: List<Int>): Flow<Result<Boolean>>
    fun deleteAllLocations(): Flow<Result<Boolean>>
    fun getWeather(locationId: Int, forceReload: Boolean): Flow<Result<WeatherEntity>>
    fun getWeatherWithoutCache(locationId: Int): Flow<Result<WeatherEntity>>
    fun saveWeather(weather: WeatherEntity): Flow<Result<Boolean>>
    fun search(query: String): Flow<Result<List<SearchEntity>>>
}