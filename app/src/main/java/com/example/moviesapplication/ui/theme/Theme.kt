package com.example.moviesapplication.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable


private val colorScheme = lightColorScheme(
surface = Blue,
onSurface = androidx.compose.ui.graphics.Color.Black,
primary = Navy,
onPrimary = LightBlue
)

@Composable
fun MoviesApplicationTheme(content: @Composable () -> Unit){
    MaterialTheme(
        colorScheme = colorScheme,
        typography = MoviesApplicationTypography,
        content = content
    )
}