package id.mrn.worldweather

import id.mrn.worldweather.ui.screen.weather.viewmodel.WeatherViewModel
import org.koin.dsl.module

val appModule = module {
    includes(platformDatabaseDriverModule())

    factory { WeatherViewModel(sdk = get()) }
}