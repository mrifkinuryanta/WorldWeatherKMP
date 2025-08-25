package id.mrn.services.di

import id.mrn.services.data.source.cache.DatabaseDataSource
import id.mrn.services.data.source.cache.DatabaseDataSourceImpl
import org.koin.dsl.module

fun dataSourceModule() = module {
    includes(platformDatabaseModule())

    single<DatabaseDataSource> {
        DatabaseDataSourceImpl(servicesDatabase = get())
    }
}