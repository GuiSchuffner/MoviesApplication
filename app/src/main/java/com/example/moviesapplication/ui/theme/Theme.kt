package com.example.moviesapplication.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable


private val colorScheme = lightColorScheme(
    surface = androidx.compose.ui.graphics.Color.Black,
    onSurface = Yellow,
    primary = Navy,
    onPrimary = Yellow
)

@Composable
fun MoviesApplicationTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = colorScheme,
        typography = MoviesApplicationTypography,
        content = content
    )
}
