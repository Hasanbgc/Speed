package com.apps.speed.android

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.apps.speed.PermissionCallBackHandler
import com.apps.speed.PermissionHandler
import com.apps.speed.SpeedProvider
import com.apps.speed.presentation.MainUI
import com.apps.speed.theme.AppTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
           AppTheme {
               val speedProvider = SpeedProvider(applicationContext)
               MainUI(
                   getPermission = {
                       PermissionHandler(this@MainActivity).getLocationPermission()
                   },
                   speedProvider
               )
           }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String?>,
        grantResults: IntArray,
        deviceId: Int
    ) {
        if(requestCode == PermissionHandler.RequestCodeLocation){
            val granted = grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED
            PermissionCallBackHandler.onPermissionResult(granted)
        }

    }
}

//@Preview
@Composable
fun MyPreView(){
    AppTheme {
       // MainUI()
    }
    //ToolBar()
}

@Preview
@Composable
fun SpeedHomePagePreview(){
    AppTheme {
        Box(modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background),
            contentAlignment = Alignment.TopCenter
        ){

        }
    }
}
