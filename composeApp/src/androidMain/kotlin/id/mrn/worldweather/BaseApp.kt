package id.mrn.worldweather

import android.app.Application
import id.mrn.worldweather.di.appModule
import org.koin.core.context.startKoin

class BaseApp : Application() {
    companion object {
        lateinit var INSTANCE: BaseApp
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this

        startKoin {
//            androidLogger(Level.DEBUG)
//            androidContext(this@MainApplication)
            modules(appModule())
        }
    }
}