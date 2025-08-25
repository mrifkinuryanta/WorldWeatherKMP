package id.mrn.services.data.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherEntity(
    @SerialName("location")
    val location: Location = Location(),
    @SerialName("current")
    val current: Current = Current(),
    @SerialName("forecast")
    val forecast: Forecast = Forecast(),
    @SerialName("alerts")
    val alerts: Alerts = Alerts()
) {
    @Serializable
    data class Location(
        @SerialName("name")
        val name: String = "",
        @SerialName("region")
        val region: String = "",
        @SerialName("country")
        val country: String = "",
        @SerialName("lat")
        val lat: Double = 0.0,
        @SerialName("lon")
        val lon: Double = 0.0,
        @SerialName("tz_id")
        val tzId: String = "",
        @SerialName("localtime_epoch")
        val localtimeEpoch: Int = 0,
        @SerialName("localtime")
        val localtime: String = ""
    )

    @Serializable
    data class Current(
        @SerialName("last_updated_epoch")
        val lastUpdatedEpoch: Int = 0,
        @SerialName("temp_c")
        val tempC: Double = 0.0,
        @SerialName("temp_f")
        val tempF: Double = 0.0,
        @SerialName("is_day")
        val isDay: Int = 0,
        @SerialName("condition")
        val condition: Condition = Condition(),
        @SerialName("wind_mph")
        val windMph: Double = 0.0,
        @SerialName("wind_kph")
        val windKph: Double = 0.0,
        @SerialName("wind_degree")
        val windDegree: Int = 0,
        @SerialName("wind_dir")
        val windDir: String = "",
        @SerialName("pressure_mb")
        val pressureMb: Int = 0,
        @SerialName("pressure_in")
        val pressureIn: Double = 0.0,
        @SerialName("humidity")
        val humidity: Int = 0,
        @SerialName("cloud")
        val cloud: Int = 0,
        @SerialName("feelslike_c")
        val feelslikeC: Int = 0,
        @SerialName("feelslike_f")
        val feelslikeF: Int = 0,
        @SerialName("heatindex_c")
        val heatindexC: Double = 0.0,
        @SerialName("heatindex_f")
        val heatindexF: Double = 0.0,
        @SerialName("dewpoint_c")
        val dewpointC: Double = 0.0,
        @SerialName("dewpoint_f")
        val dewpointF: Double = 0.0,
        @SerialName("vis_km")
        val visKm: Int = 0,
        @SerialName("vis_miles")
        val visMiles: Int = 0,
        @SerialName("uv")
        val uv: Int = 0,
        @SerialName("gust_mph")
        val gustMph: Double = 0.0,
        @SerialName("gust_kph")
        val gustKph: Double = 0.0,
        @SerialName("air_quality")
        val airQuality: AirQuality = AirQuality()
    ) {
        @Serializable
        data class Condition(
            @SerialName("text")
            val text: String = "",
            @SerialName("code")
            val code: Int = 0
        )

        @Serializable
        data class AirQuality(
            @SerialName("co")
            val co: Double = 0.0,
            @SerialName("no2")
            val no2: Double = 0.0,
            @SerialName("o3")
            val o3: Int = 0,
            @SerialName("so2")
            val so2: Double = 0.0,
            @SerialName("pm2_5")
            val pm25: Double = 0.0,
            @SerialName("pm10")
            val pm10: Double = 0.0,
            @SerialName("us-epa-index")
            val usEpaIndex: Int = 0,
            @SerialName("gb-defra-index")
            val gbDefraIndex: Int = 0
        )
    }

    @Serializable
    data class Forecast(
        @SerialName("forecastday")
        val forecastday: List<Forecastday> = listOf()
    ) {
        @Serializable
        data class Forecastday(
            @SerialName("date_epoch")
            val dateEpoch: Int = 0,
            @SerialName("day")
            val day: Day = Day(),
            @SerialName("astro")
            val astro: Astro = Astro(),
            @SerialName("hour")
            val hour: List<Hour> = listOf()
        ) {
            @Serializable
            data class Day(
                @SerialName("maxtemp_c")
                val maxtempC: Double = 0.0,
                @SerialName("maxtemp_f")
                val maxtempF: Double = 0.0,
                @SerialName("mintemp_c")
                val mintempC: Double = 0.0,
                @SerialName("mintemp_f")
                val mintempF: Double = 0.0,
                @SerialName("avgtemp_c")
                val avgtempC: Double = 0.0,
                @SerialName("avgtemp_f")
                val avgtempF: Double = 0.0,
                @SerialName("maxwind_mph")
                val maxwindMph: Double = 0.0,
                @SerialName("maxwind_kph")
                val maxwindKph: Double = 0.0,
                @SerialName("totalsnow_cm")
                val totalsnowCm: Int = 0,
                @SerialName("avgvis_km")
                val avgvisKm: Double = 0.0,
                @SerialName("avgvis_miles")
                val avgvisMiles: Int = 0,
                @SerialName("avghumidity")
                val avghumidity: Int = 0,
                @SerialName("daily_will_it_rain")
                val dailyWillItRain: Int = 0,
                @SerialName("daily_chance_of_rain")
                val dailyChanceOfRain: Int = 0,
                @SerialName("daily_will_it_snow")
                val dailyWillItSnow: Int = 0,
                @SerialName("daily_chance_of_snow")
                val dailyChanceOfSnow: Int = 0,
                @SerialName("condition")
                val condition: Condition = Condition(),
                @SerialName("uv")
                val uv: Double = 0.0,
                @SerialName("air_quality")
                val airQuality: AirQuality = AirQuality()
            ) {
                @Serializable
                data class Condition(
                    @SerialName("text")
                    val text: String = "",
                    @SerialName("code")
                    val code: Int = 0
                )

                @Serializable
                data class AirQuality(
                    @SerialName("co")
                    val co: Double = 0.0,
                    @SerialName("no2")
                    val no2: Double = 0.0,
                    @SerialName("o3")
                    val o3: Double = 0.0,
                    @SerialName("so2")
                    val so2: Double = 0.0,
                    @SerialName("pm2_5")
                    val pm25: Double = 0.0,
                    @SerialName("pm10")
                    val pm10: Double = 0.0,
                    @SerialName("us-epa-index")
                    val usEpaIndex: Int = 0,
                    @SerialName("gb-defra-index")
                    val gbDefraIndex: Int = 0
                )
            }

            @Serializable
            data class Astro(
                @SerialName("sunrise")
                val sunrise: String = "",
                @SerialName("sunset")
                val sunset: String = "",
                @SerialName("moonrise")
                val moonrise: String = "",
                @SerialName("moonset")
                val moonset: String = ""
            )

            @Serializable
            data class Hour(
                @SerialName("time_epoch")
                val timeEpoch: Int = 0,
                @SerialName("temp_c")
                val tempC: Double = 0.0,
                @SerialName("temp_f")
                val tempF: Double = 0.0,
                @SerialName("is_day")
                val isDay: Int = 0,
                @SerialName("condition")
                val condition: Condition = Condition(),
                @SerialName("wind_mph")
                val windMph: Double = 0.0,
                @SerialName("wind_kph")
                val windKph: Double = 0.0,
                @SerialName("wind_degree")
                val windDegree: Int = 0,
                @SerialName("wind_dir")
                val windDir: String = "",
                @SerialName("air_quality")
                val airQuality: AirQuality = AirQuality()
            ) {
                @Serializable
                data class Condition(
                    @SerialName("text")
                    val text: String = "",
                    @SerialName("code")
                    val code: Int = 0
                )

                @Serializable
                data class AirQuality(
                    @SerialName("co")
                    val co: Double = 0.0,
                    @SerialName("no2")
                    val no2: Double = 0.0,
                    @SerialName("o3")
                    val o3: Int = 0,
                    @SerialName("so2")
                    val so2: Double = 0.0,
                    @SerialName("pm2_5")
                    val pm25: Double = 0.0,
                    @SerialName("pm10")
                    val pm10: Double = 0.0,
                    @SerialName("us-epa-index")
                    val usEpaIndex: Int = 0,
                    @SerialName("gb-defra-index")
                    val gbDefraIndex: Int = 0
                )
            }
        }
    }

    @Serializable
    data class Alerts(
        @SerialName("alert")
        val alert: List<Any?> = listOf()
    )
}