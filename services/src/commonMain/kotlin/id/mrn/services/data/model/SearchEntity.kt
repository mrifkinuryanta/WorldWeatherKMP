package id.mrn.services.data.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchEntity(
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
    @SerialName("url")
    val url: String = ""
)