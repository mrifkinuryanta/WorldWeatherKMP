package id.mrn.worldweather

import androidx.compose.ui.window.ComposeUIViewController
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.core.logger.PrintLogger

fun MainViewController() {
    // Initialize Koin for dependency injection
    startKoin {
        logger(PrintLogger(Level.DEBUG))
        modules(appModule)
    }

    ComposeUIViewController { App() }
}