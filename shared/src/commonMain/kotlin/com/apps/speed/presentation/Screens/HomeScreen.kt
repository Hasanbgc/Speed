package com.apps.speed.presentation.Screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import com.apps.speed.domain.LiveSpeedProvider
import com.apps.speed.presentation.viewmodel.SpeedViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class HomeScreen(
    private val onRequestPermission: suspend () -> Boolean,
    private val speedProvider: LiveSpeedProvider
    ) : Screen{

    @Composable
    override fun Content(){
        val viewModel = SpeedViewModel()
        var permissionGranted = remember { false }
        val speedState = viewModel.currentSpeed.collectAsState().value
        val coroutineScope = rememberCoroutineScope()

        LaunchedEffect(Unit) {
            permissionGranted = onRequestPermission()
            //viewModel.updateLocationPermission(granted)

            if(permissionGranted) speedProvider.startSpeedUpdates { speed ->
                viewModel.updateSpeed(speed)
            }
        }

        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
            println("Speed: ${speedState.currentSpeed} km/h")
            println("Permission Granted: $permissionGranted")
            if (permissionGranted == true) {
                Text(text = "Speed: ${speedState.currentSpeed} km/h")
            } else if (permissionGranted == false) {
                Text(text = "Location permission required to show speed.")
            } else {
                Text(text = "Requesting permission...")
            }
        }
    }
}
