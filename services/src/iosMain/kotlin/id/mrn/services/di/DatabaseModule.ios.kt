package id.mrn.services.di

import id.mrn.services.cache.ServicesDatabase
import id.mrn.services.data.source.cache.IOSDatabaseDriverFactory
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun platformDatabaseModule(): Module = module {
    single<ServicesDatabase> {
        ServicesDatabase(
            IOSDatabaseDriverFactory().createDriver(dbName = "WorldWeather.db")
        )
    }
}