package com.example.searchserials.service.repository

import com.example.searchserials.service.model.Serial
import io.reactivex.Single
import java.util.*

class ApiRestRepository(val api: TVMazeApi) : Repository {
    override fun searchSerials(query: String): Single<ArrayList<Serial>> {
        return api.searchSerials(query)
    }
}