package com.apps.speed

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.security.Permission
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

actual class PermissionHandler(private val activity: Activity){

    actual suspend fun getLocationPermission(): Boolean = suspendCoroutine { continuation ->
        val permissions = listOf<String>(
            android.Manifest.permission.ACCESS_FINE_LOCATION,
            android.Manifest.permission.ACCESS_COARSE_LOCATION)

        if(ContextCompat.checkSelfPermission(activity, permissions[0]) == PackageManager.PERMISSION_GRANTED){
            continuation.resume(true)
        }else{
            ActivityCompat.requestPermissions(activity, permissions.toTypedArray(),RequestCodeLocation )
            PermissionCallBackHandler.registerCallBack { granted ->
                continuation.resume(granted)
            }
        }
    }

   /* fun onRequestPermissionResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray){
        if(requestCode == RequestCodeLocation){
            val permissionGranted = grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED
            callback?.invoke(permissionGranted)
            callback = null
        }
    }*/
    companion object{
        const val RequestCodeLocation = 100
    }
}

actual fun createPermissionHandler(): PermissionHandler {
    // This will need to be called from Android-specific code that has access to Activity
    throw IllegalStateException("PermissionHandler must be created with Activity context. Use PermissionHandler(activity) directly.")
}
