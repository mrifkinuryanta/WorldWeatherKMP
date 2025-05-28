package id.mrn.worldweather

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform