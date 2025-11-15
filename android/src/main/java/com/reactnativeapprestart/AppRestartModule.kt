package com.reactnativeapprestart

import android.content.Intent
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import android.util.Log

class AppRestartModule(reactContext: ReactApplicationContext) :
    ReactContextBaseJavaModule(reactContext) {

    override fun getName(): String = "AppRestartModule"

    @ReactMethod
    fun reload() {
        val currentActivity = reactApplicationContext.currentActivity
        if (currentActivity == null) {
            Log.e("AppRestartModule", "Current activity is null")
            return
        }

        try {
            Log.i("AppRestartModule", "Restarting app...")
            
            val packageManager = currentActivity.packageManager
            val intent = packageManager.getLaunchIntentForPackage(currentActivity.packageName)

            if (intent != null) {
                // Clear task and start fresh
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                currentActivity.startActivity(intent)
                
                // Exit immediately for instant restart
                Runtime.getRuntime().exit(0)
            } else {
                Log.e("AppRestartModule", "Could not get launch intent")
            }
        } catch (e: Exception) {
            Log.e("AppRestartModule", "Failed to restart app", e)
        }
    }
}

