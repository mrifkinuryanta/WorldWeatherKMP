package id.mrn.services.di

import org.koin.dsl.module

fun servicesModule() = module {
    includes(dispatcherModule())
    includes(sourceModule())
    includes(repositoryModule())
    includes(domainModule())
}