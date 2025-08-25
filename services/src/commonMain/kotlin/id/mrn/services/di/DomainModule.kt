package id.mrn.services.di

import id.mrn.services.domain.DeleteAllLocationsUseCase
import id.mrn.services.domain.DeleteLocationsUseCase
import id.mrn.services.domain.GetAllLocationsUseCase
import id.mrn.services.domain.GetWeatherUseCase
import id.mrn.services.domain.GetWeatherWithoutCacheUseCase
import id.mrn.services.domain.SaveWeatherUseCase
import id.mrn.services.domain.SearchUseCase
import org.koin.dsl.module

fun domainModule() = module {
    single<GetAllLocationsUseCase> {
        GetAllLocationsUseCase(weatherRepository = get())
    }

    single<DeleteLocationsUseCase> {
        DeleteLocationsUseCase(weatherRepository = get())
    }

    single<DeleteAllLocationsUseCase> {
        DeleteAllLocationsUseCase(weatherRepository = get())
    }

    single<GetWeatherUseCase> {
        GetWeatherUseCase(weatherRepository = get())
    }

    single<GetWeatherWithoutCacheUseCase> {
        GetWeatherWithoutCacheUseCase(weatherRepository = get())
    }

    single<SaveWeatherUseCase> {
        SaveWeatherUseCase(weatherRepository = get())
    }

    single<SearchUseCase> {
        SearchUseCase(weatherRepository = get())
    }
}