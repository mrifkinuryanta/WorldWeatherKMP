package id.mrn.services.domain

import id.mrn.services.data.model.WeatherEntity
import id.mrn.services.data.repository.WeatherRepository

class GetAllLocationsUseCase(private val weatherRepository: WeatherRepository) {
    operator fun invoke() = weatherRepository.getAllLocations()
}

class DeleteLocationsUseCase(private val weatherRepository: WeatherRepository) {
    operator fun invoke(locationsIds: List<Int>) = weatherRepository.deleteLocations(locationsIds)
}

class DeleteAllLocationsUseCase(private val weatherRepository: WeatherRepository) {
    operator fun invoke() = weatherRepository.deleteAllLocations()
}

class GetWeatherUseCase(private val weatherRepository: WeatherRepository) {
    operator fun invoke(locationId: Int, forceReload: Boolean) =
        weatherRepository.getWeather(locationId, forceReload)
}

class GetWeatherWithoutCacheUseCase(private val weatherRepository: WeatherRepository) {
    operator fun invoke(locationId: Int) = weatherRepository.getWeatherWithoutCache(locationId)
}

class SaveWeatherUseCase(private val weatherRepository: WeatherRepository) {
    operator fun invoke(weather: WeatherEntity) = weatherRepository.saveWeather(weather)
}

class SearchUseCase(private val weatherRepository: WeatherRepository) {
    operator fun invoke(query: String) = weatherRepository.search(query)
}