package com.task.a_decade_of_movies.core

import android.app.Application
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class ADecadeOfMoviesApp: Application() {

    override fun onCreate() {
        super.onCreate()
        AppPreferences.initializePreferences(this)
    }
}