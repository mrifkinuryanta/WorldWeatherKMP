package id.mrn.worldweather.data.model

import org.jetbrains.compose.resources.DrawableResource
import worldweather.composeapp.generated.resources.Res
import worldweather.composeapp.generated.resources.img_empty
import worldweather.composeapp.generated.resources.img_offline
import worldweather.composeapp.generated.resources.img_search
import worldweather.composeapp.generated.resources.img_welcoming

enum class EmptyStatusEnum(
    val title: String,
    val placeholder: String,
    val image: DrawableResource
) {
    SEARCH(
        title = "Search for a city",
        placeholder = "Search",
        image = Res.drawable.img_search
    ),
    EMPTY_SEARCH(
        title = "No results found",
        placeholder = "Please check you have the right spelling,or try different keywords.",
        image = Res.drawable.img_empty
    ),
    FIRST_RUN_APP(
        title = "Welcome to World Weather App!",
        placeholder = "Search for a city to get started.",
        image = Res.drawable.img_welcoming
    ),
    OFFLINE(
        title = "No internet connection",
        placeholder = "You don't seem to be connected to the internet. Please check your connection and try again.",
        image = Res.drawable.img_offline
    )
}