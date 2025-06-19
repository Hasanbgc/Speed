package com.apps.speed.presentation

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import cafe.adriel.voyager.core.screen.uniqueScreenKey
import cafe.adriel.voyager.navigator.CurrentScreen
import cafe.adriel.voyager.navigator.Navigator
import com.apps.speed.domain.LiveSpeedProvider
import com.apps.speed.presentation.Navigation.drawerItems
import com.apps.speed.presentation.Screens.AboutScreen
import com.apps.speed.presentation.Screens.HomeScreen
import com.apps.speed.presentation.Screens.ProfileScreen
import com.apps.speed.presentation.Screens.SettingsScreen
import com.apps.speed.presentation.component.ExitAlertDialog
import com.apps.speed.presentation.component.TitlePosition
import com.apps.speed.presentation.component.TopAppBar
import com.apps.speed.presentation.viewmodel.SpeedViewModel
import kotlinx.coroutines.launch


@Composable
fun MainUI(getPermission: suspend () -> Boolean = { false },SpeedProvider: LiveSpeedProvider){
    val homeScreen = HomeScreen(getPermission, SpeedProvider)
    Navigator(homeScreen){ navigator ->
        var drawerState = rememberDrawerState(DrawerValue.Closed)
        val scope = rememberCoroutineScope()
        var title by remember { mutableStateOf("Home") }
        var showDialog by remember { mutableStateOf(false) }

        LaunchedEffect(navigator.items) {
            when(navigator.lastItem){
                is HomeScreen -> { title = "Home" }
                is ProfileScreen -> { title = "Profile" }
                is SettingsScreen -> { title = "Settings" }
                is AboutScreen -> { title = "About" }
                else -> { title = "Speed" }
            }
        }

        //BackHandler(drawerState.isOpen) {

        ModalNavigationDrawer(
            drawerState = drawerState,
            scrimColor = Color.Black.copy(alpha = 0.5f),
            drawerContent = {
                val shape: Shape = RoundedCornerShape(topEnd = 16.dp, bottomEnd = 16.dp)
                ModalDrawerSheet(
                    drawerShape = shape
                ) {
                    drawerItems.forEach { item ->
                        NavigationDrawerItem(
                            label = { Text(text = item.title) },
                            selected = false,
                            icon = { Icon(imageVector = item.icon, contentDescription = item.title) },
                            onClick = {
                                val screen = if (item.title == "Home") homeScreen else item.screen
                                navigator.push(screen)
                                title = item.title
                                scope.launch {  //Scope is a rememberCoroutineScope, which is use to launch a short lived coroutine that perform some small UI related task
                                    drawerState.close()
                                }
                            }
                        )
                    }
                }
            }
        ) {
            Scaffold(
                topBar = {
                    TopAppBar(
                    title = title,
                    titlePosition = TitlePosition.Center,
                    titleColor = Color.Blue,
                    navigationIcon = if (title == "Home")Icons.Default.Menu else Icons.Filled.ArrowBack,
                    navigationIconColor = Color.Blue,
                    onNavigationClick = {
                        if (title == "Home"){
                            scope.launch {
                                drawerState.open()
                            }
                        }else {
                            navigator.pop()
                        }
                    },
                    backgroundColor = lightColorScheme().onPrimary
                )},
                content = { padding ->
                    if(showDialog){
                        ExitAlertDialog()
                    }

                    Box(modifier = Modifier.padding(padding)){
                        CurrentScreen()
                    }

                }
            )

        }

    }
}
