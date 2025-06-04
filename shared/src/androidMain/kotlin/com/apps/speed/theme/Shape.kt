package com.apps.speed.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

val Shapes = Shapes(
    small = RoundedCornerShape(8.dp),   // For buttons, chips, etc.
    medium = RoundedCornerShape(12.dp), // Cards, dialogs
    large = RoundedCornerShape(0.dp)    // Large containers, sharp corners
)