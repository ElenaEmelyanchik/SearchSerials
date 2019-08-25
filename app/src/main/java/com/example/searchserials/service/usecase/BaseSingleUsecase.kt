package com.example.searchserials.service.usecase

import com.example.searchserials.service.repository.Repository
import io.reactivex.Single

abstract class BaseSingleUsecase<T> {
    open lateinit var repository: Repository
    protected abstract fun executeResponse(): Single<T>

    fun execute(): Single<T> {
        try {
            return Single.defer { executeResponse() }
        } catch (e: Exception) {
            return Single.error(e)
        }

    }
}