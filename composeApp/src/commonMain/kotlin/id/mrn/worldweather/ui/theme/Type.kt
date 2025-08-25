package id.mrn.worldweather.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import org.jetbrains.compose.resources.Font
import worldweather.composeapp.generated.resources.Poppins_Bold
import worldweather.composeapp.generated.resources.Poppins_Italic
import worldweather.composeapp.generated.resources.Poppins_Light
import worldweather.composeapp.generated.resources.Poppins_Medium
import worldweather.composeapp.generated.resources.Poppins_Regular
import worldweather.composeapp.generated.resources.Poppins_SemiBold
import worldweather.composeapp.generated.resources.Res

val poppinsFamily: FontFamily
    @Composable get() = FontFamily(
        Font(Res.font.Poppins_Bold, FontWeight.Bold),
        Font(Res.font.Poppins_SemiBold, FontWeight.SemiBold),
        Font(Res.font.Poppins_Light, FontWeight.Light),
        Font(Res.font.Poppins_Regular, FontWeight.Normal),
        Font(Res.font.Poppins_Italic, FontWeight.Normal, FontStyle.Italic),
        Font(Res.font.Poppins_Medium, FontWeight.Medium)
    )

private val defaultTypography = Typography()
val Typography: Typography
    @Composable get() = Typography(
        displayLarge = defaultTypography.displayLarge.copy(fontFamily = poppinsFamily),
        displayMedium = defaultTypography.displayMedium.copy(fontFamily = poppinsFamily),
        displaySmall = defaultTypography.displaySmall.copy(fontFamily = poppinsFamily),

        headlineLarge = defaultTypography.headlineLarge.copy(fontFamily = poppinsFamily),
        headlineMedium = defaultTypography.headlineMedium.copy(fontFamily = poppinsFamily),
        headlineSmall = defaultTypography.headlineSmall.copy(fontFamily = poppinsFamily),

        titleLarge = defaultTypography.titleLarge.copy(fontFamily = poppinsFamily),
        titleMedium = defaultTypography.titleMedium.copy(fontFamily = poppinsFamily),
        titleSmall = defaultTypography.titleSmall.copy(fontFamily = poppinsFamily),

        bodyLarge = defaultTypography.bodyLarge.copy(fontFamily = poppinsFamily),
        bodyMedium = defaultTypography.bodyMedium.copy(fontFamily = poppinsFamily),
        bodySmall = defaultTypography.bodySmall.copy(fontFamily = poppinsFamily),

        labelLarge = defaultTypography.labelLarge.copy(fontFamily = poppinsFamily),
        labelMedium = defaultTypography.labelMedium.copy(fontFamily = poppinsFamily),
        labelSmall = defaultTypography.labelSmall.copy(fontFamily = poppinsFamily)
    )