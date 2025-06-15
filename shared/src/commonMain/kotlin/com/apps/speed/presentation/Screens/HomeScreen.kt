package com.apps.speed.presentation.Screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.core.screen.Screen
import com.apps.speed.domain.LiveSpeedProvider
import com.apps.speed.presentation.viewmodel.SpeedViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.roundToInt


class HomeScreen(
    private val onRequestPermission: suspend () -> Boolean,
    private val speedProvider: LiveSpeedProvider
    ) : Screen{

    @Composable
    override fun Content(){
        val viewModel = remember { SpeedViewModel(speedProvider)}
        var permissionGranted by remember { mutableStateOf<Boolean?>(null) }
        val speedState = viewModel.currentSpeed.collectAsStateWithLifecycle()
        val coroutineScope = rememberCoroutineScope()

        LaunchedEffect(Unit) {
            permissionGranted = onRequestPermission()

            if(permissionGranted == true) {
                viewModel.updateSpeed()
            }
        }

        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){

            when (permissionGranted) {
                true -> Text(text = "Speed: ${speedState.value.currentSpeed.toFormattedSpeed()} km/h")
                false -> Text("Permission required")
                null -> {}//CircularProgressIndicator()
            }
        }
    }

    fun Float.toFormattedSpeed(): String {
        return ((this * 1000).roundToInt() / 1000.0).toString()
    }
}
