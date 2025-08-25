package id.mrn.worldweather.util

import androidx.compose.runtime.Composable
import worldweather.composeapp.generated.resources.Res

object Util {
    @Composable
    fun getWeatherIcon(id: Int, isDay: Boolean): String {
        val themeFolder = if (isDay) "day" else "night"
        val path = "files/$themeFolder/$id.png"

        return Res.getUri(path)
    }
}