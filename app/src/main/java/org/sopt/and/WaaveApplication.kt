package org.sopt.and

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class WaaveApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}
