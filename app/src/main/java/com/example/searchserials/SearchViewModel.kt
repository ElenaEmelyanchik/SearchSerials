package com.example.searchserials

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

class SearchViewModel  : ViewModel() {
    private val compositeDisposable = CompositeDisposable()
    private val listSerials = MutableLiveData<ArrayList<Serial>>()

    fun search(newText: String) {
        val serial = Serial("test")
        val list = ArrayList<Serial>()
        list.add(serial)
        listSerials.value = list
    }

    override fun onCleared() {
        super.onCleared()
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
    }

    fun serials() = listSerials
}