package com.example.timer

import android.app.Application
import com.example.timer.di.mainScreen
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(mainScreen)
        }
    }
}