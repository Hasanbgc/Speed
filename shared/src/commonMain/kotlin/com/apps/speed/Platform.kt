package com.apps.speed

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform