package com.edu.academy.core.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class ConcreteApp: Application() {

    override fun onCreate() {
        super.onCreate()
        AppPreferences.initializePreferences(this)
    }
}