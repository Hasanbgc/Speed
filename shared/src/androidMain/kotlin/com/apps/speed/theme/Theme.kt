package com.apps.speed.theme

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


private val LightColorScheme = lightColorScheme(
    primary = Colors.ElectricBlue,
    onPrimary = Color.White,
    secondary = Colors.VividMagenta,
    onSecondary = Color.White,
    background = Colors.Background,
    onBackground = Colors.OnBackground,
    surface = Colors.Surface,
    onSurface = Colors.OnBackground,
    error = Color(0xFFB00020),        // default error color
    onError = Color.White
)

@Composable
fun AppTheme(
    content: @Composable () -> Unit
){
    MaterialTheme(
        colorScheme =  LightColorScheme,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}