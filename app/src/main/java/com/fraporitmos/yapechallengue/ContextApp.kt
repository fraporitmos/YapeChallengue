package com.fraporitmos.yapechallengue

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ContextApp: Application() {
    companion object {
        lateinit var context: ContextApp
    }

    override fun onCreate() {
        super.onCreate()
        context = this
    }
}