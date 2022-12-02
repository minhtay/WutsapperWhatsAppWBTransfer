package com.wondershare.wutsapper.transfer.common

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class App : Application() {

    companion object{
        lateinit var app: App
    }

    init {
        app = this
    }

    override fun onCreate() {

        // Setup Timber
        Timber.plant(Timber.DebugTree())

        super.onCreate()
    }


}