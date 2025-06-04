package com.apps.speed.domain

interface LiveSpeedProvider {
    fun startSpeedUpdates(onSpeedChanged: (Int) -> Unit)
    fun stopSpeedUpdates()
}
