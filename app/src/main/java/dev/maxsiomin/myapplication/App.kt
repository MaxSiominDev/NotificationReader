package dev.maxsiomin.myapplication

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import dev.maxsiomin.myapplication.core.util.isDebug
import timber.log.Timber

@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()

        if (isDebug()) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
