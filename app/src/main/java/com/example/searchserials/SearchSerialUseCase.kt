package com.example.searchserials

import io.reactivex.Single

class SearchSerialUseCase(override var repository: Repository, val query: String): BaseSingleUsecase<ArrayList<Serial>>() {

    override fun executeResponse(): Single<ArrayList<Serial>> {
        return repository.searchSerials(query)
    }
}