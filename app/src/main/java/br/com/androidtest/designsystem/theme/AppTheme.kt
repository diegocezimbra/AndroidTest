package br.com.androidtest.designsystem.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColors = lightColorScheme(
    primary = BrandBlue,
    secondary = BrandCyan,
    background = SurfaceBackground,
    surface = SurfaceWhite,
    onPrimary = SurfaceWhite,
    onSecondary = Ink900,
    onBackground = Ink900,
    onSurface = Ink900
)

private val DarkColors = darkColorScheme(
    primary = BrandBlue,
    secondary = BrandCyan
)

@Composable
fun AndroidTestTheme(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColors else LightColors,
        typography = AppTypography,
        content = content
    )
}
