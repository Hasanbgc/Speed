package com.apps.speed

expect class PermissionHandler{
   suspend fun getLocationPermission() : Boolean
}

expect fun createPermissionHandler(): PermissionHandler
