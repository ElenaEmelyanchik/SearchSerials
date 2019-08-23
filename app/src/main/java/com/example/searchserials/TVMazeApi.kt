package com.example.searchserials

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface TVMazeApi {

    @GET("/search/shows")
    fun searchSerials(@Query("q") text: String): Single<ArrayList<Serial>>
}