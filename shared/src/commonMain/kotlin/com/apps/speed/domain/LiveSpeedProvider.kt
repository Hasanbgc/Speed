package com.apps.speed.domain

interface LiveSpeedProvider {
    fun startSpeedUpdates(onSpeedChanged: (Float) -> Unit)
    fun stopSpeedUpdates()
}
