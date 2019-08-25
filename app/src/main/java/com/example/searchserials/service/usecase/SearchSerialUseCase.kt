package com.example.searchserials.service.usecase

import com.example.searchserials.service.model.Serial
import com.example.searchserials.service.repository.Repository
import io.reactivex.Single

class SearchSerialUseCase(override var repository: Repository, val query: String): BaseSingleUsecase<ArrayList<Serial>>() {

    override fun executeResponse(): Single<ArrayList<Serial>> {
        return repository.searchSerials(query)
    }
}