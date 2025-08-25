package id.mrn.services.di

import id.mrn.services.data.repository.WeatherRepository
import id.mrn.services.data.repository.WeatherRepositoryImpl
import org.koin.core.qualifier.named
import org.koin.dsl.module

fun repositoryModule() = module {
    single<WeatherRepository> {
        WeatherRepositoryImpl(
            databaseDataSource = get(),
            weatherApiDataSource = get(),
            ioDispatcher = get(named(IO_DISPATCHER))
        )
    }
}