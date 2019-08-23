package com.example.searchserials

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ContextModule::class, NetworkModule::class])
interface AppComponent {

    fun repository(): Repository
    fun inject(activity: MainActivity)
}