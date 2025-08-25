package id.mrn.services.data.source.network

import id.mrn.services.data.model.SearchEntity
import id.mrn.services.data.model.WeatherEntity

interface WeatherApiDataSource {
    suspend fun getWeather(query: String): WeatherEntity
    suspend fun search(query: String): List<SearchEntity>
}