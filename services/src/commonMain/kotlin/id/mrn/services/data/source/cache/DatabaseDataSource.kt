package id.mrn.services.data.source.cache

import id.mrn.services.data.model.SearchEntity
import id.mrn.services.data.model.WeatherEntity

internal interface DatabaseDataSource {
    fun insertOrReplace(locationId: Int, weather: WeatherEntity): Boolean
    fun getAllLocations(): List<WeatherEntity.Location>
    fun deleteLocations(locationIds: List<Int>): Boolean
    fun deleteAllLocations(): Boolean
    fun getWeather(locationId: Int): WeatherEntity
}