package com.example.searchserials

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.internal.platform.Platform
import okhttp3.internal.platform.Platform.INFO
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    @LoggingInterceptor
    fun provideLoggingInterceptor(): Interceptor {
        val loggingInterceptor = HttpLoggingInterceptor { message ->
            Platform.get().log(INFO, message, null)
        }
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return loggingInterceptor
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://api.tvmaze.com ")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    internal fun provideOkHttpClient(@LoggingInterceptor loggingInterceptor: Interceptor): OkHttpClient =
        OkHttpClient
        .Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    @Provides
    @Singleton
    fun provideGson(): Gson =
        GsonBuilder().registerTypeAdapter(Serial::class.java, SerialDeserialization()).setLenient().create()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): TVMazeApi {
        return retrofit.create(TVMazeApi::class.java)
    }

    @Provides
    @Singleton
    internal fun provideRepository(api: TVMazeApi): Repository {
        return ApiRestRepository(api)
    }
}