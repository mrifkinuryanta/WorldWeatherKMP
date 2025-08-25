package id.mrn.services.di

import id.mrn.services.cache.ServicesDatabase
import id.mrn.services.data.source.cache.AndroidDatabaseDriverFactory
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun platformDatabaseModule(): Module = module {
    single<ServicesDatabase> {
        ServicesDatabase(
            AndroidDatabaseDriverFactory(context = androidContext()).createDriver(
                dbName = "WorldWeather.db"
            )
        )
    }
}