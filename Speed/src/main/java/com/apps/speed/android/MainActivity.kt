package com.apps.speed.android

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.apps.speed.PermissionCallBackHandler
import com.apps.speed.PermissionHandler
import com.apps.speed.SpeedProvider
import com.apps.speed.presentation.MainUI
import com.apps.speed.theme.AppTheme
import com.apps.speed.theme.Colors

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
        if (requestCode == PermissionHandler.RequestCodeLocation) {
            val granted =
                grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED
            PermissionCallBackHandler.onPermissionResult(granted)
        }

    }
}

//@Preview
@Composable
fun MyPreView() {
    AppTheme {
        // MainUI()
    }
    //ToolBar()
}

@Preview
@Composable
fun SpeedHomePagePreview() {
    AppTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background),
            contentAlignment = Alignment.TopCenter
        ) {

            Column(modifier = Modifier.wrapContentHeight().wrapContentWidth().paddingFromBaseline(top = 140.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                //speed preview
                Row() {
                  /*  Text(
                        text = "Speed : ",
                        modifier = Modifier.wrapContentSize(),
                        color = MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.headlineLarge
                    )

                    Spacer(modifier = Modifier.size(8.dp))*/

                    Text(
                        text = "420",
                        modifier = Modifier
                            .wrapContentHeight()
                            .wrapContentWidth(),
                        color = MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.headlineLarge
                    )

                    Spacer(modifier = Modifier.size(8.dp))

                    Text(
                        text = "km/h",
                        modifier = Modifier
                            .wrapContentWidth()
                            .wrapContentHeight(),
                        color = MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.headlineLarge
                    )

                }

                //max speed preview
                Row() {
                    Text(
                        text = "Top Speed : ",
                        modifier = Modifier.wrapContentSize(),
                        color = MaterialTheme.colorScheme.onPrimary,
                        style = MaterialTheme.typography.titleLarge
                    )
                    Spacer(modifier = Modifier.size(8.dp))
                    Text(
                        text = "420",
                        modifier = Modifier
                            .wrapContentHeight()
                            .wrapContentWidth(),
                        color = Colors.VibrantCyan,
                        style = MaterialTheme.typography.titleLarge
                    )
                    Spacer(modifier = Modifier.size(8.dp))
                    Text(
                        text = "km/h",
                        modifier = Modifier
                            .wrapContentWidth()
                            .wrapContentHeight(),
                        color = Colors.VibrantCyan,
                        style = MaterialTheme.typography.titleLarge
                    )
                }

            }
        }
    }
}