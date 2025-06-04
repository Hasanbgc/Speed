package com.apps.speed

actual class PermissionHandler {
    actual suspend fun getLocationPermission(): Boolean {
        // iOS implementation here - TODO: Implement actual permission check
        return true // placeholder
    }
}

actual fun createPermissionHandler(): PermissionHandler {
    return PermissionHandler()
}
