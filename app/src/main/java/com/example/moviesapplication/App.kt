package com.example.moviesapplication

import android.app.Application
import com.example.moviesapplication.movies_feature.di.module
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(listOf(module))
        }
    }
}
