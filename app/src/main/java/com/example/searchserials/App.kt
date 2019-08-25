package com.example.searchserials

import android.app.Application
import com.example.searchserials.di.AppComponent
import com.example.searchserials.di.ContextModule
import com.example.searchserials.di.DaggerAppComponent
import com.example.searchserials.di.NetworkModule

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