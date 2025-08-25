package id.mrn.services.di

import id.mrn.services.data.source.cache.DatabaseDataSource
import id.mrn.services.data.source.cache.DatabaseDataSourceImpl
import id.mrn.services.data.source.network.WeatherApiDataSource
import id.mrn.services.data.source.network.WeatherApiDataSourceImpl
import id.mrn.services.util.Constant
import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

fun sourceModule() = module {
    includes(platformDatabaseModule())

    single<HttpClient> {
        HttpClient {
            install(ContentNegotiation) {
                json(
                    Json {
                        ignoreUnknownKeys = true
                        useAlternativeNames = false
                    }
                )
            }
            install(DefaultRequest) {
                url {
                    // Set the base URL for all requests
                    protocol = URLProtocol.HTTPS
                    host = Constant.BASE_URL
                }
            }
        }
    }

    single<DatabaseDataSource> {
        DatabaseDataSourceImpl(servicesDatabase = get())
    }

    single<WeatherApiDataSource> {
        WeatherApiDataSourceImpl(httpClient = get())
    }
}