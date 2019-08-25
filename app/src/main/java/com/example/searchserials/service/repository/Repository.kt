package com.example.searchserials.service.repository

import com.example.searchserials.service.model.Serial
import io.reactivex.Single
import java.util.*

interface Repository {

    fun searchSerials(query: String): Single<ArrayList<Serial>>
}