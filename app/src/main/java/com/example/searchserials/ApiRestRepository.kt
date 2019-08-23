package com.example.searchserials

import io.reactivex.Single
import java.util.ArrayList

class ApiRestRepository(val api: TVMazeApi) : Repository {
    override fun searchSerials(query: String): Single<ArrayList<Serial>> {
        return api.searchSerials(query)
    }
}