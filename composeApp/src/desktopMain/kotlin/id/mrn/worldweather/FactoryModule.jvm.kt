package id.mrn.worldweather

import id.mrn.services.SpaceXSDK
import id.mrn.services.cache.DesktopDatabaseDriverFactory
import id.mrn.services.network.SpaceXApi
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun platformDatabaseDriverModule(): Module = module {
    single<SpaceXApi> { SpaceXApi() }
    single<SpaceXSDK> {
        SpaceXSDK(
            databaseDriverFactory = DesktopDatabaseDriverFactory(),
            api = get()
        )
    }
}