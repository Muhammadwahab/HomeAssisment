package com.appcenter.homeexercise.application

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class HomeApp : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}