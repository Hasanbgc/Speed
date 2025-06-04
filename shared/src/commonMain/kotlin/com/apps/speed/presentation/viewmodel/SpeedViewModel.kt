package com.apps.speed.presentation.viewmodel

import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest


class SpeedViewModel : ViewModel()  {

    private val _currentSpeed = MutableStateFlow<SpeedState>(SpeedState(currentSpeed = 0))
    val currentSpeed: StateFlow<SpeedState> = _currentSpeed

    private val _locationPermission = MutableStateFlow<Boolean?>(null)
    val locationPermission: StateFlow<Boolean?> = _locationPermission

    fun updateSpeed(newSpeed: Int) {
        _currentSpeed.value = _currentSpeed.value.copy(currentSpeed = newSpeed)
    }

    fun resetSpeed() {
        _currentSpeed.value = SpeedState(currentSpeed = 0)
    }

    fun updateLocationPermission(granted: Boolean) {
        _locationPermission.value = granted
    }

    // Collect speed updates only when permission is granted
    /*val currentSpeed: StateFlow<SpeedState> = locationPermission.flatMapLatest { granted ->
        if (granted) {
            speedProvider.startSpeedUpdates()
        } else {
            flowOf(0) // Default speed when no permission
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = 0
    ).map { SpeedState(it) }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), SpeedState(0))*/
}

data class SpeedState(
        val currentSpeed: Int,
)
