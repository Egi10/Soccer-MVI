package com.bajapuik.soccer_mvi

import android.app.Application
import com.bajapuik.soccer_mvi.base.CoreApplication
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

class SoccerApp: Application() {
    override fun onCreate() {
        super.onCreate()

        CoreApplication.initialize {
            androidContext(this@SoccerApp)
            androidLogger()
        }
    }
}