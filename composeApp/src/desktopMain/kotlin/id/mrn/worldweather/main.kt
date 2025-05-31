package id.mrn.worldweather

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.logger.Level
import org.koin.core.logger.PrintLogger

fun main() {
    startKoin {
        logger(PrintLogger(Level.DEBUG))
        modules(appModule)
    }

    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "World Weather",
        ) {
            App()
        }
    }
}

