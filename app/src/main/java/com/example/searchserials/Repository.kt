package com.example.searchserials

import io.reactivex.Single
import java.util.ArrayList

interface Repository {

    fun searchSerials(query: String): Single<ArrayList<Serial>>
}