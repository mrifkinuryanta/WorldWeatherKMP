package id.mrn.worldweather.di

import id.mrn.services.di.servicesModule
import org.koin.dsl.module

fun appModule() = module {
    includes(servicesModule())
    includes(viewmodelModule())
}