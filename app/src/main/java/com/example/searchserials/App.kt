package com.example.searchserials

import android.app.Application

class App : Application() {
    private val component: AppComponent by lazy {
        DaggerAppComponent
            .builder()
            .contextModule(ContextModule(this))
            .networkModule(NetworkModule())
            .build()
    }

    fun component() = component
}