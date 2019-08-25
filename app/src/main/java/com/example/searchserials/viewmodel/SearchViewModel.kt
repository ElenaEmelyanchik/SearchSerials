package com.example.searchserials.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.searchserials.service.model.Serial
import com.example.searchserials.service.repository.Repository
import com.example.searchserials.service.usecase.SearchSerialUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class SearchViewModel(val repository: Repository) : ViewModel() {
    private val compositeDisposable = CompositeDisposable()
    private val listSerials = MutableLiveData<ArrayList<Serial>>()

    fun search(query: String) {

        if (query.length < 2) return

        compositeDisposable.add(
            SearchSerialUseCase(repository, query).execute().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe(
                {
                    listSerials.value = it
                },
                {
                    Log.e(this.javaClass.name, it.message)
                }
            ))
    }

    override fun onCleared() {
        super.onCleared()
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
    }

    fun serials() = listSerials
}