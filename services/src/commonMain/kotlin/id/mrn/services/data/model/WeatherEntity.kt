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
        @SerialName("id")
        val id: Int = 0,
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
        val localtimeEpoch: Long = 0L
    )

    @Serializable
    data class Current(
        @SerialName("last_updated_epoch")
        val lastUpdatedEpoch: Long = 0L,
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
        val feelsLikeC: Int = 0,
        @SerialName("feelslike_f")
        val feelsLikeF: Int = 0,
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
        val forecastDay: List<ForecastDay> = listOf()
    ) {
        @Serializable
        data class ForecastDay(
            @SerialName("date_epoch")
            val dateEpoch: Long = 0L,
            @SerialName("day")
            val day: Day = Day(),
            @SerialName("hour")
            val hour: List<Hour> = listOf()
        ) {
            @Serializable
            data class Day(
                @SerialName("maxtemp_c")
                val maxTempC: Double = 0.0,
                @SerialName("maxtemp_f")
                val maxTempF: Double = 0.0,
                @SerialName("mintemp_c")
                val minTempC: Double = 0.0,
                @SerialName("mintemp_f")
                val minTempF: Double = 0.0,
                @SerialName("avgtemp_c")
                val avgTempC: Double = 0.0,
                @SerialName("avgtemp_f")
                val avgTempF: Double = 0.0,
                @SerialName("maxwind_mph")
                val maxWindMph: Double = 0.0,
                @SerialName("maxwind_kph")
                val maxWindKph: Double = 0.0,
                @SerialName("totalsnow_cm")
                val totalSnowCm: Int = 0,
                @SerialName("avgvis_km")
                val avgVisKm: Double = 0.0,
                @SerialName("avgvis_miles")
                val avgVisMiles: Int = 0,
                @SerialName("avghumidity")
                val avgHumidity: Int = 0,
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
                val uv: Double = 0.0
            ) {
                @Serializable
                data class Condition(
                    @SerialName("text")
                    val text: String = "",
                    @SerialName("code")
                    val code: Int = 0
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
                val moonSet: String = ""
            )

            @Serializable
            data class Hour(
                @SerialName("time_epoch")
                val timeEpoch: Long = 0L,
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
                val windDir: String = ""
            ) {
                @Serializable
                data class Condition(
                    @SerialName("text")
                    val text: String = "",
                    @SerialName("code")
                    val code: Int = 0
                )
            }
        }
    }

    @Serializable
    data class Alerts(
        @SerialName("alert")
        val alerts: List<Alert> = listOf()
    ) {
        @Serializable
        data class Alert(
            @SerialName("headline")
            val headline: String = "",
            @SerialName("msgtype")
            val msgType: String = "",
            @SerialName("severity")
            val severity: String = "",
            @SerialName("urgency")
            val urgency: String = "",
            @SerialName("areas")
            val areas: String = "",
            @SerialName("category")
            val category: String = "",
            @SerialName("certainty")
            val certainty: String = "",
            @SerialName("event")
            val event: String = "",
            @SerialName("note")
            val note: String = "",
            @SerialName("effective")
            val effective: String = "",
            @SerialName("expires")
            val expires: String = "",
            @SerialName("desc")
            val desc: String = "",
            @SerialName("instruction")
            val instruction: String = ""
        )
    }
}