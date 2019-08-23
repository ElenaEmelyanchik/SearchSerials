package com.example.searchserials

import android.content.Context
import com.google.android.exoplayer2.*
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.trackselection.TrackSelector
import dagger.Module
import dagger.Provides

@Module
class ExoPlayerModule {

    @Provides
    fun providePlayer(renderersFactory:RenderersFactory, trackSelector: TrackSelector, loadControl:LoadControl): ExoPlayer = ExoPlayerFactory.newSimpleInstance(
        renderersFactory, trackSelector, loadControl)

    @Provides
    fun provideTrackSelector():TrackSelector = DefaultTrackSelector()

    @Provides
    fun provideFactory(context: Context):RenderersFactory = DefaultRenderersFactory(context)

    @Provides
    fun provideLoadControl():LoadControl = DefaultLoadControl()
}