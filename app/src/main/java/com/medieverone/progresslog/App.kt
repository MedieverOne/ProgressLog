package com.medieverone.progresslog

import android.app.Application
import android.content.Context
import com.medieverone.progresslog.di.AppComponent
import com.medieverone.progresslog.di.DaggerAppComponent

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        AppComponent.init(
            DaggerAppComponent.builder()
                .build()
        )
    }

    companion object {
        @Volatile
        lateinit var appContext: Context
            private set
    }
}