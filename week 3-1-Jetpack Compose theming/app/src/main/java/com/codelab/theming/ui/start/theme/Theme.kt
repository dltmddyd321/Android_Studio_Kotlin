package com.codelab.theming.ui.start.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun JetnewsTheme(darkTheme: Boolean = isSystemInDarkTheme(),content: @Composable () -> Unit) {
    MaterialTheme(colors = if(darkTheme) DarkColors else LightColors, content = content, typography = JetnewsTypography, shapes = JetnewsShapes)
}

private val DarkColors = darkColors(
    primary = Red300,
    primaryVariant = Red700,
    onPrimary = Color.Black,
    secondary = Red300,
    onSecondary = Color.Black,
    error = Red200
)