package com.apps.speed.presentation.Navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.PointerIcon
import cafe.adriel.voyager.core.screen.Screen
import com.apps.speed.presentation.Screens.AboutScreen
import com.apps.speed.presentation.Screens.ProfileScreen
import com.apps.speed.presentation.Screens.SettingsScreen

// Placeholder screen for Home since HomeScreen needs dynamic creation
object PlaceholderHomeScreen : Screen {
    @androidx.compose.runtime.Composable
    override fun Content() = Unit
}

data class DrawerItem(
    val icon: ImageVector,
    val title: String,
    val isDivider: Boolean = false,
    val screen:Screen
)

val drawerItems = listOf(
    DrawerItem(icon = Icons.Filled.Home, title = "Home", screen = PlaceholderHomeScreen),
    DrawerItem(icon = Icons.Filled.Person, title = "Profile", screen = ProfileScreen),
    DrawerItem(icon = Icons.Filled.Settings, title = "Settings", screen = SettingsScreen),
    DrawerItem(icon = Icons.Filled.Info, title = "About", screen = AboutScreen),
)
