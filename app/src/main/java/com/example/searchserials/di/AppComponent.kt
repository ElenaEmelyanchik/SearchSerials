package com.example.searchserials.di

import com.example.searchserials.service.repository.Repository
import com.example.searchserials.view.ui.MainActivity
import com.example.searchserials.view.ui.VideoActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ContextModule::class, NetworkModule::class, ExoPlayerModule::class])
interface AppComponent {

    fun repository(): Repository
    fun inject(activity: MainActivity)
    fun inject(activity: VideoActivity)
}