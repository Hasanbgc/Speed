package com.apps.speed.theme

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

object Colors {
    val NeonBlue = Color(0xFF00FFFF)
    val NeonPurple = Color(0xFFBF00FF)
    val NeonPink = Color(0xFFFF00FF)
    val NeonGreen = Color(0xFF39FF14)

    val Background = Color(0xff0b101c)
    val AccentYellow = Color(0xFFFFFF33)
    val GlowWhite = Color(0xFFE0E0E0)

    val OnBackground = GlowWhite
    val OnPrimary = Background

    val ElectricBlue = Color(0xFF2979FF)
    val VividMagenta = Color(0xFFD81B60)
    val VibrantCyan = Color(0xFF00B8D4)
    val NeonCoral = Color(0xFFFF6F61)

    val Surface = Color(0xFF1E1E1E)

    val DeepTealShade = Color(0xFF094744)
    val DarkBackground = Color(0xFF0D1112)

    val gradientBackground = Brush.linearGradient(
        colors = listOf(
            DarkBackground, // Dark background
            DeepTealShade  // Deep teal
        )
    )

}