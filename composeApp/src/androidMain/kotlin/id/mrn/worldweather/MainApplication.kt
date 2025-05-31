package id.mrn.worldweather

import android.app.Application
import org.koin.core.context.GlobalContext.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
//            androidLogger(Level.DEBUG)
//            androidContext(this@MainApplication)
            modules(appModule)
        }
    }
}