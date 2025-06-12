package com.apps.speed

import android.Manifest
import android.content.Context
import android.os.Looper
import androidx.annotation.RequiresPermission
import com.apps.speed.domain.LiveSpeedProvider
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SpeedProvider(context: Context): LiveSpeedProvider {

    private val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
    private var locationCallback: LocationCallback? = null

    @RequiresPermission(allOf = [Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION])
    override fun startSpeedUpdates(onSpeedChanged: (Float) -> Unit) {
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                locationResult.lastLocation?.let {
                    println("location_chaged ${it.speed}")
                    onSpeedChanged(it.speed)
                }
            }
        }
        val locationRequest = LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, 1000).build()
        fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback!!, Looper.getMainLooper())

    }

    override fun stopSpeedUpdates() {
        locationCallback?.let { fusedLocationClient.removeLocationUpdates(it) }
    }
}
/*
fun mockSpeed(){
    // Simulate accelerating from 0 to 100 km/h
    var speed = 0f
    CoroutineScope(Dispatchers.Default).launch {
        while (speed < 100) {
            delay(1000) // Update every second
            speed += 5f
            callback(speed)
        }
}*/
