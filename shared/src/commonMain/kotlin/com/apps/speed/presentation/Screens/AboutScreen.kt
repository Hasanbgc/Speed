package com.apps.speed.presentation.Screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen

object AboutScreen :Screen{
    @Composable
    override fun Content(){
        Text(text = "About Screen")
    }
}