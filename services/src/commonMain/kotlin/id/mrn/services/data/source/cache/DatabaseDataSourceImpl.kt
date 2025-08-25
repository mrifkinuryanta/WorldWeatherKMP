package id.mrn.services.data.source.cache

import id.mrn.services.cache.ServicesDatabase

internal class DatabaseSourceImpl(databaseDriverFactory: DatabaseDriverFactory) {
    private val database =
        ServicesDatabase(databaseDriverFactory.createDriver(dbName = "WorldWeather.db"))

}