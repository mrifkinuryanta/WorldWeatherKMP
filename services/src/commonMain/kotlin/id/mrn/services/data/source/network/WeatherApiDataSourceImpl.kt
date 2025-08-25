package id.mrn.services.data.source.network

import id.mrn.services.data.model.SearchEntity
import id.mrn.services.data.model.WeatherEntity
import id.mrn.services.util.Constant.API_KEY
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class WeatherApi(private val httpClient: HttpClient) {
    suspend fun getWeatherData(city: String): WeatherEntity {
        return httpClient
            .get("/forecast.json?key=$API_KEY&q=$city&days=3&aqi=yes&alerts=yes")
            .body()
    }

    suspend fun search(query: Any): List<SearchEntity> {
        return httpClient
            .get("/search.json?key=$API_KEY&q=$query")
            .body()
    }
}