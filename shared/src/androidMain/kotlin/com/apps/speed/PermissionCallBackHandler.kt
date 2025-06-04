package com.apps.speed

object PermissionCallBackHandler {
    private var callback: ((Boolean) -> Unit )?= null

    fun registerCallBack(callback: (Boolean) -> Unit) {
        this.callback = callback
    }

    fun onPermissionResult(isGranted: Boolean){
        callback?.invoke(isGranted)
        callback = null
    }
}