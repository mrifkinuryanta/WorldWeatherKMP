package id.mrn.services.data.source.cache

import id.mrn.services.cache.ServicesDatabase
import id.mrn.services.data.model.WeatherEntity
import id.mrn.services.util.generateUUID

internal class DatabaseDataSourceImpl(
    private val servicesDatabase: ServicesDatabase
) : DatabaseDataSource {
    private val locationQueries = servicesDatabase.locationQueries
    private val currentWeatherReportQueries = servicesDatabase.currentWeatherReportQueries
    private val airQualityQueries = servicesDatabase.airQualityQueries
    private val forecastDayQueries = servicesDatabase.forecastDayQueries
    private val forecastHourQueries = servicesDatabase.forecastHourQueries
    private val alertQueries = servicesDatabase.alertQueries

    override fun insertOrReplace(locationId: Int, weather: WeatherEntity): Boolean {
        val airQualityId = generateUUID()
        val weatherId = generateUUID()

        return try {
            with(weather.location) {
                locationQueries.insertOrReplaceLocation(
                    id = locationId.toLong(),
                    name = name,
                    region = region,
                    country = country,
                    lat = lat,
                    lon = lon,
                    tz_id = tzId,
                    localtime_epoch = localtimeEpoch
                )
            }
            with(weather.current.airQuality) {
                airQualityQueries.insertAirQuality(
                    id = airQualityId,
                    co = co,
                    no2 = no2,
                    o3 = o3.toDouble(),
                    so2 = so2,
                    pm2_5 = pm25,
                    pm10 = pm10,
                    us_epa_index = usEpaIndex.toLong(),
                    gb_defra_index = gbDefraIndex.toLong()
                )
            }
            with(weather.current) {
                currentWeatherReportQueries.insertCurrentWeatherReport(
                    id = weatherId,
                    location_id = locationId.toLong(),
                    last_updated_epoch = lastUpdatedEpoch,
                    temp_c = tempC,
                    temp_f = tempF,
                    feelslike_c = feelsLikeC.toDouble(),
                    feelslike_f = feelsLikeF.toDouble(),
                    is_day = isDay.toLong(),
                    wind_kph = windKph,
                    wind_mph = windMph,
                    wind_degree = windDegree.toLong(),
                    wind_dir = windDir,
                    pressure_mb = pressureMb.toDouble(),
                    pressure_in = pressureIn.toDouble(),
                    humidity = humidity.toLong(),
                    cloud = cloud.toLong(),
                    vis_km = visKm.toDouble(),
                    vis_miles = visMiles.toDouble(),
                    gust_mph = gustMph,
                    gust_kph = gustKph,
                    uv = uv.toDouble(),
                    condition_code = condition.code.toLong(),
                    condition_text = condition.text,
                    current_air_quality_id = airQualityId
                )
            }
            with(weather.forecast) {
                forecastDay.map { item ->
                    val forecastDayId = generateUUID()
                    forecastDayQueries.insertForecastDay(
                        id = forecastDayId,
                        current_weather_report_id = weatherId,
                        location_id = locationId.toLong(),
                        date_epoch = item.dateEpoch,
                        maxtemp_c = item.day.maxTempC,
                        maxtemp_f = item.day.maxTempF,
                        mintemp_c = item.day.minTempC,
                        mintemp_f = item.day.minTempF,
                        maxwind_kph = item.day.maxWindKph,
                        maxwind_mph = item.day.maxWindMph,
                        condition_code = item.day.condition.code.toLong(),
                        condition_text = item.day.condition.text
                    )
                    item.hour.map { hourItem ->
                        forecastHourQueries.insertForecastHour(
                            id = generateUUID(),
                            forecast_day_id = forecastDayId,
                            time_epoch = hourItem.timeEpoch,
                            temp_c = hourItem.tempC,
                            temp_f = hourItem.tempF,
                            is_day = hourItem.isDay.toLong(),
                            condition_code = hourItem.condition.code.toLong()
                        )
                    }
                }
            }
            with(weather.alerts) {
                alerts.map { item ->
                    servicesDatabase.alertQueries.insertAlert(
                        id = generateUUID(),
                        current_weather_report_id = weatherId,
                        headline = item.headline,
                        msg_type = item.msgType,
                        severity = item.severity,
                        urgency = item.urgency,
                        areas = item.areas,
                        category = item.category,
                        certainty = item.certainty,
                        event = item.event,
                        note = item.note,
                        effective = item.effective,
                        expires = item.expires,
                        description = item.desc,
                        instruction = item.instruction
                    )
                }
            }
            true
        } catch (e: Exception) {
            false
        }
    }

    override fun getAllLocations(): List<WeatherEntity.Location> {
        return try {
            val locations = locationQueries.getAllLocations().executeAsList()
            return locations.map { item ->
                WeatherEntity.Location(
                    id = item.id.toInt(),
                    name = item.name,
                    region = item.region,
                    country = item.country,
                    lat = item.lat,
                    lon = item.lon,
                    tzId = item.tz_id,
                    localtimeEpoch = item.localtime_epoch
                )
            }
        } catch (e: Exception) {
            emptyList()
        }
    }

    override fun deleteLocations(locationIds: List<Int>): Boolean {
        return try {
            locationIds.forEach { id ->
                locationQueries.deleteLocationById(id.toLong())
            }
            true
        } catch (e: Exception) {
            false
        }
    }

    override fun deleteAllLocations(): Boolean {
        return try {
            locationQueries.deleteAllLocations()
            true
        } catch (e: Exception) {
            false
        }
    }

    override fun getWeather(locationId: Int): WeatherEntity {
        return try {
            val location =
                locationQueries.getLocationById(locationId.toLong()).executeAsOne()
            val currentWeatherReport =
                currentWeatherReportQueries.getLatestCurrentWeatherReportForLocation(
                    locationId.toLong()
                ).executeAsOne()
            val airQuality = airQualityQueries.getAirQualityById(
                currentWeatherReport.current_air_quality_id
            ).executeAsOneOrNull()
            val forecastDays =
                forecastDayQueries.getForecastDaysForLocation(locationId.toLong()).executeAsList()
            val alerts = alertQueries.getAlertsForReport(currentWeatherReport.id).executeAsList()

            return WeatherEntity(
                location = WeatherEntity.Location(
                    id = location.id.toInt(),
                    name = location.name,
                    region = location.region,
                    country = location.country,
                    lat = location.lat,
                    lon = location.lon,
                    tzId = location.tz_id,
                    localtimeEpoch = location.localtime_epoch
                ),
                current = WeatherEntity.Current(
                    lastUpdatedEpoch = currentWeatherReport.last_updated_epoch,
                    tempC = currentWeatherReport.temp_c,
                    tempF = currentWeatherReport.temp_f,
                    isDay = currentWeatherReport.is_day.toInt(),
                    condition = WeatherEntity.Current.Condition(
                        code = currentWeatherReport.condition_code.toInt(),
                        text = currentWeatherReport.condition_text
                    ),
                    windKph = currentWeatherReport.wind_kph,
                    windMph = currentWeatherReport.wind_mph,
                    windDegree = currentWeatherReport.wind_degree.toInt(),
                    windDir = currentWeatherReport.wind_dir,
                    pressureMb = currentWeatherReport.pressure_mb.toInt(),
                    pressureIn = currentWeatherReport.pressure_in,
                    humidity = currentWeatherReport.humidity.toInt(),
                    cloud = currentWeatherReport.cloud.toInt(),
                    feelsLikeC = currentWeatherReport.feelslike_c.toInt(),
                    feelsLikeF = currentWeatherReport.feelslike_f.toInt(),
                    visKm = currentWeatherReport.vis_km.toInt(),
                    visMiles = currentWeatherReport.vis_miles.toInt(),
                    uv = currentWeatherReport.uv.toInt(),
                    gustMph = currentWeatherReport.gust_mph,
                    gustKph = currentWeatherReport.gust_kph,
                    airQuality = airQuality?.let {
                        WeatherEntity.Current.AirQuality(
                            co = airQuality.co,
                            no2 = airQuality.no2,
                            o3 = airQuality.o3.toInt(),
                            so2 = airQuality.so2,
                            pm25 = airQuality.pm2_5,
                            pm10 = airQuality.pm10,
                            usEpaIndex = airQuality.us_epa_index.toInt(),
                            gbDefraIndex = airQuality.gb_defra_index.toInt()
                        )
                    } ?: WeatherEntity.Current.AirQuality()
                ),
                forecast = WeatherEntity.Forecast(
                    forecastDay = forecastDays.map { item ->
                        WeatherEntity.Forecast.ForecastDay(
                            dateEpoch = item.date_epoch,
                            day = WeatherEntity.Forecast.ForecastDay.Day(
                                maxTempC = item.maxtemp_c,
                                maxTempF = item.maxtemp_f,
                                minTempC = item.mintemp_c,
                                minTempF = item.mintemp_f,
                                maxWindKph = item.maxwind_kph,
                                maxWindMph = item.maxwind_mph,
                                condition = WeatherEntity.Forecast.ForecastDay.Day.Condition(
                                    code = item.condition_code.toInt(),
                                    text = item.condition_text
                                )
                            ),
                            hour = servicesDatabase.forecastHourQueries
                                .getForecastHoursForDay(item.id)
                                .executeAsList()
                                .map { hourItem ->
                                    WeatherEntity.Forecast.ForecastDay.Hour(
                                        timeEpoch = hourItem.time_epoch,
                                        tempC = hourItem.temp_c,
                                        tempF = hourItem.temp_f,
                                        isDay = hourItem.is_day.toInt(),
                                        condition = WeatherEntity.Forecast.ForecastDay.Hour.Condition(
                                            code = hourItem.condition_code.toInt()
                                        )
                                    )
                                }
                        )
                    }
                ),
                alerts = WeatherEntity.Alerts(
                    alerts = alerts.map { alert ->
                        WeatherEntity.Alerts.Alert(
                            headline = alert.headline,
                            msgType = alert.msg_type,
                            severity = alert.severity,
                            urgency = alert.urgency,
                            areas = alert.areas,
                            category = alert.category,
                            certainty = alert.certainty,
                            event = alert.event,
                            note = alert.note,
                            effective = alert.effective,
                            expires = alert.expires,
                            desc = alert.description,
                            instruction = alert.instruction
                        )
                    }
                )
            )
        } catch (e: Exception) {
            WeatherEntity()
        }
    }
}