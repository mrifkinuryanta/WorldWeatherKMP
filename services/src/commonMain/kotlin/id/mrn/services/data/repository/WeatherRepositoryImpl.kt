package id.mrn.services.data.repository

import id.mrn.services.base.Result
import id.mrn.services.data.model.SearchEntity
import id.mrn.services.data.model.WeatherEntity
import id.mrn.services.data.source.cache.DatabaseDataSource
import id.mrn.services.data.source.network.WeatherApiDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

internal class WeatherRepositoryImpl(
    private val databaseDataSource: DatabaseDataSource,
    private val weatherApiDataSource: WeatherApiDataSource,
    private val ioDispatcher: CoroutineDispatcher
) : WeatherRepository {
    override fun getAllLocations(): Flow<Result<List<WeatherEntity.Location>>> = flow {
        val locations = databaseDataSource.getAllLocations()
        if (locations.isEmpty()) {
            emit(Result.Error("No locations found"))
        } else {
            emit(Result.Success(locations))
        }
    }.catch { e ->
        emit(Result.Error(e.message ?: "An error occurred while fetching locations"))
    }.flowOn(ioDispatcher)

    override fun deleteLocations(locationsIds: List<Int>): Flow<Result<Boolean>> = flow {
        val result = databaseDataSource.deleteLocations(locationsIds)
        if (result) {
            emit(Result.Success(true))
        } else {
            emit(Result.Error("Failed to delete locations"))
        }
    }.catch { e ->
        emit(Result.Error(e.message ?: "An error occurred while deleting locations"))
    }.flowOn(ioDispatcher)

    override fun deleteAllLocations(): Flow<Result<Boolean>> = flow {
        val result = databaseDataSource.deleteAllLocations()
        if (result) {
            emit(Result.Success(true))
        } else {
            emit(Result.Error("Failed to delete all locations"))
        }
    }.catch { e ->
        emit(Result.Error(e.message ?: "An error occurred while deleting all locations"))
    }.flowOn(ioDispatcher)

    override fun getWeather(locationId: Int, forceReload: Boolean): Flow<Result<WeatherEntity>> =
        flow {
            val weather = databaseDataSource.getWeather(locationId)
            if (forceReload || weather.location.id == 0) {
                val newWeather = weatherApiDataSource.getWeather(locationId.toString())
                if (databaseDataSource.insertOrReplace(locationId, newWeather)) {
                    emit(Result.Success(newWeather))
                } else {
                    emit(Result.Error("Failed to update weather data"))
                }
            } else {
                emit(Result.Success(weather))
            }
        }.catch { e ->
            emit(Result.Error(e.message ?: "An error occurred while fetching weather data"))
        }.flowOn(ioDispatcher)

    override fun getWeatherWithoutCache(locationId: Int): Flow<Result<WeatherEntity>> = flow {
        val weather = weatherApiDataSource.getWeather(locationId.toString())
        if (weather.location.id == 0) {
            emit(Result.Error("Failed to fetch weather data for location ID: $locationId"))
        } else {
            emit(Result.Success(weather))
        }
    }.catch { e ->
        emit(
            Result.Error(e.message ?: "An error occurred while fetching weather data without cache")
        )
    }.flowOn(ioDispatcher)

    override fun saveWeather(weather: WeatherEntity): Flow<Result<Boolean>> = flow {
        val result = databaseDataSource.insertOrReplace(weather.location.id, weather)
        if (result) {
            emit(Result.Success(true))
        } else {
            emit(Result.Error("Failed to save weather data"))
        }
    }.catch { e ->
        emit(Result.Error(e.message ?: "An error occurred while saving weather data"))
    }.flowOn(ioDispatcher)

    override fun search(query: String): Flow<Result<List<SearchEntity>>> = flow {
        val results = weatherApiDataSource.search(query)
        if (results.isEmpty()) {
            emit(Result.Error("No search results found for query: $query"))
        } else {
            emit(Result.Success(results))
        }
    }.catch { e ->
        emit(Result.Error(e.message ?: "An error occurred while searching for: $query"))
    }.flowOn(ioDispatcher)
}