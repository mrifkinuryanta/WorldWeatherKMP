package id.mrn.worldweather.di

import id.mrn.worldweather.ui.screen.search.viewmodel.SearchViewModel
import id.mrn.worldweather.ui.screen.weather.viewmodel.WeatherViewModel
import org.koin.dsl.module

fun viewmodelModule() = module {
    factory { WeatherViewModel(searchUseCase = get()) }
    factory {
        SearchViewModel(
            getAllLocationsUseCase = get(),
            searchUseCase = get(),
            deleteLocationsUseCase = get()
        )
    }
}